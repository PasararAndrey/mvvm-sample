@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.detekt) apply true
}

project.dependencies.detektPlugins(libs.detekt.formatting)

true // Needed to make the Suppress annotation work for the plugins block
