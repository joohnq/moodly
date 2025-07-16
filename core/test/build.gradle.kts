plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
}

kotlin {
    sourceSets {
        androidUnitTest.dependencies {
            implementation(libs.android.driver)
        }
        commonMain.dependencies {
            implementation(libs.kotlin.datetime)
            implementation(projects.core.database)
            implementation(libs.bundles.test)
            implementation(libs.coroutines.extensions)
            implementation(libs.turbine)
        }
        commonTest.dependencies {
            implementation(projects.core.database)
            implementation(libs.bundles.test)
            implementation(libs.coroutines.extensions)
        }
        nativeTest.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
    }
}