plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")

    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.serialization)
            implementation(libs.coroutines.core)

            implementation(libs.bundles.koin)
        }
    }
}