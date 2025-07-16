plugins {
    alias(libs.plugins.serialization)
    id("moodly.multiplatform.library")
}

android {
    namespace = "com.joohnq.core.datastore"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.dataStore)
            implementation(libs.bundles.koin)
        }
    }
}