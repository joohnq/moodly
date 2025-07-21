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
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.user.ui)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.feature.freudScore.api)
            implementation(projects.sharedResources)
            implementation(projects.core.ui)
            implementation(projects.core.domain)

            implementation(libs.bundles.viewmodel)
            implementation(libs.serialization)
            implementation(libs.bundles.koin)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
            implementation(libs.turbine)
        }
    }
}

dependencies {
    debugImplementation(libs.androidx.ui.tooling)
}