import io.gitlab.arturbosch.detekt.extensions.DetektExtension

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.ktlint.gradle) apply true
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
}

allprojects.onEach { project ->
    project.afterEvaluate {
        with(project.plugins) {
            if (hasPlugin(libs.plugins.kotlinAndroid.get().pluginId)) {
                apply(libs.plugins.detekt.get().pluginId)
                apply(libs.plugins.ktlint.gradle.get().pluginId)
                project.extensions.configure<DetektExtension> {
                    config.setFrom(rootProject.files("default-detekt-config.yml"))
                    buildUponDefaultConfig = true
                }
                project.dependencies.detektPlugins(libs.detekt.formatting.get())
            }
        }
    }
}

true // Needed to make the Suppress annotation work for the plugins block
