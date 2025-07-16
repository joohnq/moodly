plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
    alias(libs.plugins.serialization)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.sharedResources)
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.feature.mood.ui)
            implementation(projects.feature.splash.ui)
            implementation(projects.feature.mood.domain)
            implementation(projects.core.test)

            implementation(libs.bundles.viewmodel)
            implementation(libs.serialization)
            implementation(libs.kotlin.datetime)
            implementation(libs.bundles.koin)

            implementation(libs.calendar)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
            implementation(libs.turbine)
        }
    }
}