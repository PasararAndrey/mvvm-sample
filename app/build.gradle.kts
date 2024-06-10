import java.util.Locale
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    jacoco
}

android {
    namespace = "com.example.mvvmsample"
    compileSdk = 34

//    project.tasks.preBuild.dependsOn("detekt").dependsOn("ktlintCheck")
    defaultConfig {
        applicationId = "com.example.mvvmsample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        // Removes needless resource configurations from apk
        resourceConfigurations += setOf("en")

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")} \"")
        testInstrumentationRunner = "com.example.mvvmsample.HiltTestRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = File(rootDir, "samplekey.jks")
            keyPassword = "123456"
            storePassword = "123456"
            keyAlias = "pasarar"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs["release"]
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "kotlin-serialization-proguard-rules.pro",
            )
            // config strings for database name and network url should be changed according to valid ones
            buildConfigField("String", "DB_NAME_BOOK", "\"book_database\"")
            buildConfigField("String", "BASE_URL", "\"https://www.google.com/\"")

            ndk {
                abiFilters += setOf("armeabi-v7a", "arm64-v8a", "x86_64")
            }
        }

        debug {
            isDebuggable = true
            // config strings for database name and network url should be updated according to valid ones
            buildConfigField("String", "DB_NAME_BOOK", "\"book_database\"")
            buildConfigField("String", "BASE_URL", "\"https://api.bigbookapi.com/\"")

            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            // Excludes needless files from builds
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/okhttp3/internal/publicsuffix/NOTICE"
            excludes += "/META-INF/com.google.*.version"
            excludes += "/META-INF/kotlinx_*.version"
            excludes += "/DebugProbesKt.bin"
        }
    }
}

dependencies {
    //region Core
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    //endregion
    //region Compose
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.navigation.compose)
    //endregion
    //region DI
    implementation(libs.dagger.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.dagger.hilt.compiler)
    //endregion
    //region Room
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)
    //endregion
    //region Network
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlin.serialization)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.retrofit.adapters.result)
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.coil.compose)
    implementation(libs.gson)
    //endregion
    //region Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    //endregion
    //region Local Tests
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    kspAndroidTest(libs.dagger.hilt.android.compiler)
    //endregion
    //region Instrumented Tests
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.dagger.hilt.android.testing)
    //endregion
    //region Debug
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    debugImplementation(libs.leakcanary.android)
    //endregion
}

val exclusions = listOf(
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
)

tasks.withType(Test::class) {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}

android {
    applicationVariants.all(
        closureOf<com.android.build.gradle.internal.api.BaseVariantImpl> {
            val variant = this@closureOf.name.replaceFirstChar {
                if (it.isLowerCase()) {
                    it.titlecase(
                        Locale.getDefault(),
                    )
                } else {
                    it.toString()
                }
            }

            val unitTests = "test${variant}UnitTest"
            val androidTests = "connected${variant}AndroidTest"

            tasks.register<JacocoReport>("jacoco${variant}CodeCoverage") {
                dependsOn(listOf(unitTests, androidTests))
                group = "Reporting"
                description = "Execute ui and unit tests, generate and combine Jacoco coverage report"
                reports {
                    xml.required.set(true)
                    html.required.set(true)
                }
                sourceDirectories.setFrom(layout.projectDirectory.dir("src/main"))
                classDirectories.setFrom(
                    files(
                        fileTree(layout.buildDirectory.dir("intermediates/javac/")) {
                            exclude(exclusions)
                        },
                        fileTree(layout.buildDirectory.dir("tmp/kotlin-classes/")) {
                            exclude(exclusions)
                        },
                    ),
                )
                executionData.setFrom(
                    files(
                        fileTree(layout.buildDirectory) { include(listOf("**/*.exec", "**/*.ec")) },
                    ),
                )
            }
        },
    )
}
