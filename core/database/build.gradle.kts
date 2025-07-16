plugins {
    id("moodly.multiplatform.library")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.android.driver)
        }
        commonMain.dependencies {
            implementation(libs.kotlin.datetime)

            implementation(libs.bundles.koin)
        }
        nativeMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
    }
}