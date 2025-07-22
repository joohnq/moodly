plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)

            implementation(projects.core.domain)

            implementation(projects.core.navigation)

            implementation(projects.sharedResources)

            implementation(projects.feature.mood.api)
            implementation(projects.feature.mood.impl)

            implementation(projects.feature.stressLevel.api)
            implementation(projects.feature.stressLevel.impl)

            implementation(projects.feature.sleepQuality.api)
            implementation(projects.feature.sleepQuality.impl)

            implementation(projects.feature.user.api)
            implementation(projects.feature.user.impl)

            implementation(projects.feature.preferences.impl)

            implementation(libs.navigation.compose)
            implementation(libs.kotlin.datetime)

            implementation(libs.bundles.viewmodel)
            implementation(libs.bundles.koin)
        }
    }
}