plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.navigation)

            implementation(projects.core.ui)

            implementation(projects.core.domain)

            implementation(projects.sharedResources)

            implementation(projects.feature.preferences.impl)

            implementation(libs.navigation.compose)

            implementation(libs.bundles.koin)
        }
    }
}

dependencies {
    debugImplementation(libs.androidx.ui.tooling)
}
