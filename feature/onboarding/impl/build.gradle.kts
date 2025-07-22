plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.sharedResources)
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.mood.api)
            implementation(projects.feature.mood.impl)
            implementation(projects.feature.stressLevel.api)
            implementation(projects.feature.stressLevel.impl)
            implementation(projects.feature.sleepQuality.api)
            implementation(projects.feature.sleepQuality.impl)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.user.ui)
            implementation(projects.feature.preferences.impl)
            implementation(projects.core.navigation)

            implementation(libs.navigation.compose)

            implementation(libs.kotlin.datetime)
            implementation(libs.bundles.viewmodel)
            implementation(libs.bundles.koin)
        }
    }
}