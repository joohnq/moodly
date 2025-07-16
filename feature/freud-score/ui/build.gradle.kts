plugins {
    id("moodly.multiplatform.library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.sharedResources)
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.freudScore.domain)
            implementation(projects.feature.mood.ui)
            implementation(projects.feature.splash.ui)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.bundles.viewmodel)

            implementation(libs.bundles.koin)

            implementation(libs.kotlin.datetime)
        }
    }
}