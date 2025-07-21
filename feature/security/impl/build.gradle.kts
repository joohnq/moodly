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
            implementation(projects.feature.security.api)

            implementation(libs.coroutines.core)
            implementation(libs.bundles.koin)
            implementation(libs.bundles.dataStore)
            implementation(libs.serialization)

            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.sharedResources)
            implementation(projects.feature.preferences.impl)
            implementation(projects.core.navigation)

            implementation(libs.navigation.compose)
        }
    }
}