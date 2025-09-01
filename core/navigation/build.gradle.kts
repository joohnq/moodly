plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")

    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.serialization)
            implementation(libs.compose.navigation)
            implementation(libs.kotlin.datetime)
        }
    }
}
