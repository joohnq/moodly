plugins {
    id("moodly.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.koin)
        }
    }
}