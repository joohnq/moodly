plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.preferences.api)
            implementation(projects.sharedResources)
            implementation(projects.core.datastore)
            implementation(libs.bundles.koin)
            implementation(libs.bundles.dataStore)

            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.preferences.api)
        }
    }
}