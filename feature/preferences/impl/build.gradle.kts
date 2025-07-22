plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.datastore)

            implementation(projects.core.ui)

            implementation(projects.core.domain)

            implementation(projects.sharedResources)

            implementation(projects.feature.preferences.api)

            implementation(libs.bundles.koin)
            implementation(libs.bundles.dataStore)
        }
    }
}