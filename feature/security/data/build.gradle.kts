plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.biometric)
        }
        commonMain.dependencies {
            implementation(projects.feature.security.domain)

            implementation(libs.coroutines.core)
            implementation(libs.bundles.koin)
            implementation(libs.bundles.dataStore)
            implementation(libs.serialization)
        }
    }
}