plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
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

            implementation(libs.navigation.compose)
        }
    }
}

dependencies {
    debugImplementation(libs.androidx.ui.tooling)
}