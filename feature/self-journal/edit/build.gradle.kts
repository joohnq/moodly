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

            implementation(projects.sharedResources)

            implementation(projects.feature.freudScore.api)
            implementation(projects.feature.freudScore.impl)

            implementation(projects.feature.mood.api)
            implementation(projects.feature.mood.impl)

            implementation(projects.feature.selfJournal.api)
            implementation(projects.feature.selfJournal.impl)

            implementation(projects.feature.stressLevel.impl)

            implementation(projects.feature.stressLevel.api)

            implementation(projects.feature.user.api)
            implementation(projects.feature.user.impl)

            implementation(libs.coroutines.extensions)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)

            implementation(libs.bundles.viewmodel)
            implementation(libs.bundles.koin)
        }
    }
}
