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

            implementation(projects.sharedResources)

            implementation(projects.feature.stressLevel.api)
            implementation(projects.feature.stressLevel.impl)

            implementation(projects.feature.mood.api)

            implementation(libs.coroutines.extensions)
            implementation(libs.serialization)
            implementation(libs.charts)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)

            implementation(libs.bundles.koin)
            implementation(libs.bundles.viewmodel)
        }
    }
}
