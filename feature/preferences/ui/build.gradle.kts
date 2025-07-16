plugins {
    id("moodly.multiplatform.library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.sharedResources)
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.preferences.domain)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.bundles.koin)
        }
    }
}