plugins {
    id("moodly.multiplatform.library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.ui.tooling)
        }
        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.security.domain)
            implementation(projects.sharedResources)
            implementation(projects.feature.preferences.ui)
            implementation(projects.core.navigation)

            implementation(libs.bundles.koin)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.navigation.compose)
        }
    }
}

dependencies {
    debugImplementation(libs.androidx.ui.tooling)
}