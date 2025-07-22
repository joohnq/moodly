plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")

    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)

            implementation(projects.core.storage.api)

            implementation(projects.core.ui)

            implementation(libs.kotlin.datetime)

            implementation(libs.bundles.koin)
        }
    }
}