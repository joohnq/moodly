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

            implementation(projects.feature.gratefulness.api)
            implementation(projects.feature.gratefulness.impl)

            implementation(projects.sharedResources)

            implementation(libs.coroutines.extensions)
            implementation(libs.serialization)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)

            implementation(libs.bundles.koin)
            implementation(libs.bundles.viewmodel)
        }
    }
}
