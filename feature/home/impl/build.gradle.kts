plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.navigation)

            implementation(projects.core.ui)

            implementation(projects.core.domain)

            implementation(projects.feature.stressLevel.add)
            implementation(projects.feature.stressLevel.api)
            implementation(projects.feature.stressLevel.history)
            implementation(projects.feature.stressLevel.impl)
            implementation(projects.feature.stressLevel.overview)

            implementation(projects.feature.sleepQuality.add)
            implementation(projects.feature.sleepQuality.api)
            implementation(projects.feature.sleepQuality.history)
            implementation(projects.feature.sleepQuality.impl)
            implementation(projects.feature.sleepQuality.overview)

            implementation(projects.feature.freudScore.api)
            implementation(projects.feature.freudScore.impl)

            implementation(projects.feature.selfJournal.add)
            implementation(projects.feature.selfJournal.api)
            implementation(projects.feature.selfJournal.history)
            implementation(projects.feature.selfJournal.impl)
            implementation(projects.feature.selfJournal.overview)

            implementation(projects.feature.user.api)
            implementation(projects.feature.user.impl)

            implementation(projects.feature.mood.api)
            implementation(projects.feature.mood.add)
            implementation(projects.feature.mood.history)
            implementation(projects.feature.mood.impl)
            implementation(projects.feature.mood.overview)

            implementation(projects.sharedResources)

            implementation(projects.feature.splash.impl)

            implementation(libs.navigation.compose)

            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)

            implementation(libs.bundles.koin)
        }
    }
}
