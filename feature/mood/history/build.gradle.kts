plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)

            implementation(projects.core.ui)

            implementation(projects.feature.mood.api)
            implementation(projects.feature.mood.impl)

            implementation(projects.feature.user.impl)

            implementation(projects.feature.user.api)

            implementation(projects.feature.sleepQuality.api)

            implementation(projects.feature.freudScore.api)

            implementation(projects.sharedResources)

            implementation(libs.coroutines.extensions)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)

            implementation(libs.bundles.koin)
            implementation(libs.bundles.viewmodel)
        }
    }
}
