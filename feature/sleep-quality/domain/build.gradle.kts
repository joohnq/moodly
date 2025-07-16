plugins {
    id("moodly.multiplatform.library")
    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)
            implementation(projects.feature.mood.domain)

            implementation(libs.kotlin.datetime)
            implementation(libs.serialization)
            implementation(libs.bundles.koin)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
        }
    }
}