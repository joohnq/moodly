plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.android.driver)
        }
        commonMain.dependencies {
            implementation(libs.bundles.koin)

            implementation(libs.kotlin.datetime)
        }
        nativeMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
    }
}
