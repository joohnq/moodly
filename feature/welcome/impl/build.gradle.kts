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

            implementation(projects.feature.welcome.api)

            implementation(projects.feature.preferences.api)

            implementation(libs.navigation.compose)

            implementation(libs.bundles.koin)
        }
    }
}
