plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")

    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.biometric)
        }
        commonMain.dependencies {
            implementation(projects.core.ui)

            implementation(projects.core.navigation)

            implementation(projects.core.domain)

            implementation(projects.sharedResources)

            implementation(projects.feature.security.api)

            implementation(projects.feature.preferences.api)

            implementation(libs.coroutines.core)
            implementation(libs.serialization)
            implementation(libs.navigation.compose)

            implementation(libs.bundles.koin)
            implementation(libs.bundles.dataStore)
        }
    }
}
