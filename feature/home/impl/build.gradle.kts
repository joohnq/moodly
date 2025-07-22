plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.navigation)
            implementation(projects.feature.stressLevel.impl)
            implementation(projects.feature.stressLevel.api)
            implementation(projects.feature.sleepQuality.impl)
            implementation(projects.feature.sleepQuality.api)
            implementation(projects.feature.freudScore.impl)
            implementation(projects.feature.freudScore.api)
            implementation(projects.feature.selfJournal.impl)
            implementation(projects.feature.selfJournal.api)
            implementation(projects.feature.user.impl)
            implementation(projects.feature.user.api)
            implementation(projects.feature.mood.impl)
            implementation(projects.feature.mood.api)
            implementation(projects.sharedResources)
            implementation(projects.feature.splash.impl)
            implementation(projects.core.ui)
            implementation(projects.core.domain)

            implementation(libs.navigation.compose)

            implementation(libs.kotlin.datetime)
            implementation(libs.bundles.koin)
            implementation(libs.calendar)
        }
    }
}