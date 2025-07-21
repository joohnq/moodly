plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.sharedResources)
            implementation(projects.feature.mood.api)
            implementation(projects.feature.freudScore.api)
            implementation(projects.feature.mood.impl)
            implementation(projects.feature.splash.ui)

            implementation(libs.bundles.viewmodel)

            implementation(libs.bundles.koin)

            implementation(libs.kotlin.datetime)
        }
    }
}