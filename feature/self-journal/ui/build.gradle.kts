plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.splash.ui)
            implementation(projects.sharedResources)
            implementation(projects.feature.freudScore.ui)
            implementation(projects.feature.freudScore.domain)
            implementation(projects.feature.mood.ui)
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.selfJournal.domain)
            implementation(projects.feature.stressLevel.ui)
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.user.ui)

            implementation(libs.bundles.viewmodel)
            implementation(libs.bundles.koin)

            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
            implementation(libs.turbine)
            implementation(libs.napier)
        }
    }
}