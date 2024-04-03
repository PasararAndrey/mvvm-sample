import io.gitlab.arturbosch.detekt.extensions.DetektExtension

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.detekt) apply true
}

allprojects.onEach { project ->
    project.afterEvaluate {
        with(project.plugins) {
            if (hasPlugin(libs.plugins.kotlinAndroid.get().pluginId)) {
                apply(
                    libs.plugins
                        .detekt
                        .get()
                        .pluginId,
                )
                project.extensions.configure<DetektExtension> {
                    config.setFrom(rootProject.files("default-detekt-config.yml"))
                }
                project.dependencies.detektPlugins(libs.detekt.formatting.get())
            }
        }
    }
}

true // Needed to make the Suppress annotation work for the plugins block
