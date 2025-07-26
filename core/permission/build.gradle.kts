plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.accompanist.permissions)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(libs.bundles.koin)
        }
    }
}
