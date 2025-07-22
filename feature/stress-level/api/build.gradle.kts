plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")

    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)

            implementation(libs.serialization)
            implementation(libs.kotlin.datetime)

            implementation(libs.bundles.koin)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
        }
    }
}