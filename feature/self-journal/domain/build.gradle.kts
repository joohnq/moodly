plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.freudScore.api)

            implementation(libs.kotlin.datetime)
            implementation(libs.bundles.koin)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
        }
    }
}