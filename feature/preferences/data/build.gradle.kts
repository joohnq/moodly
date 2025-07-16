plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.preferences.domain)
            implementation(projects.core.datastore)
            implementation(libs.bundles.koin)
            implementation(libs.bundles.dataStore)
        }
    }
}