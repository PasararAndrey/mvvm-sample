import java.io.FileInputStream
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
}

val propertiesFile = project.rootProject.file("local.properties")
val properties = Properties()
properties.load(FileInputStream(propertiesFile))

android {
    namespace = "com.findyourbook"
    compileSdk = 34

//    project.tasks.preBuild.dependsOn("detekt").dependsOn("ktlintCheck")
    defaultConfig {
        applicationId = "com.findyourbook"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "0.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        // Removes needless resource configurations from apk
        resourceConfigurations += setOf("en")
        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")} \"")
        testInstrumentationRunner = "com.example.findyourbook.HiltTestRunner"
    }

    signingConfigs {
        create("release_config") {
            storeFile = File(rootDir, properties["STORE_FILE"].toString())
            keyPassword = properties["KEY_PASSWORD"].toString()
            storePassword = properties["STORE_PASSWORD"].toString()
            keyAlias = properties["KEY_ALIAS"].toString()
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            signingConfig = signingConfigs["release_config"]
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "kotlin-serialization-proguard-rules.pro",
                "retrofit-proguard-rules.pro",
            )
            // config strings for database name and network url should be changed according to valid ones
            buildConfigField("String", "DB_NAME_BOOK", "\"book_database\"")
            buildConfigField("String", "BASE_URL", "\"https://api.bigbookapi.com/\"")

            ndk {
                abiFilters += setOf("armeabi-v7a", "arm64-v8a", "x86_64")
            }
        }

        debug {
            isDebuggable = true
            // config strings for database name and network url should be updated according to valid ones
            buildConfigField("String", "DB_NAME_BOOK", "\"book_database\"")
            buildConfigField("String", "BASE_URL", "\"https://api.bigbookapi.com/\"")
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

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    //region Core
    implementation(libs.core.ktx)
    implementation(libs.core.splashscreen)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.kotlinx.collections.immutable)
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
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.paging.testing)

    kspAndroidTest(libs.dagger.hilt.android.compiler)
    //endregion
    //region Instrumented Tests
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.ext.truth)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.dagger.hilt.android.testing)
    androidTestImplementation(libs.truth)
    //endregion
    //region Debug
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    debugImplementation(libs.leakcanary.android)
    //endregion
}
