plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")

    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.dataStore)
            implementation(libs.bundles.koin)
        }
    }
}