plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.database)

            implementation(projects.core.domain)

            implementation(projects.core.ui)

            implementation(projects.sharedResources)

            implementation(projects.feature.sleepQuality.api)
            implementation(projects.feature.sleepQuality.impl)

            implementation(projects.feature.mood.api)
            implementation(projects.feature.mood.impl)

            implementation(projects.feature.splash.impl)

            implementation(libs.coroutines.extensions)
            implementation(libs.serialization)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)

            implementation(libs.bundles.koin)
            implementation(libs.bundles.viewmodel)
        }
    }
}
