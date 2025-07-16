plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.datetime)
            implementation(libs.bundles.koin)
            implementation(libs.calendar)

            implementation(libs.coroutines.core)
            implementation(libs.coroutines.extensions)

            implementation(libs.navigation.compose)
        }
    }
}