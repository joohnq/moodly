plugins {
    id("moodly.multiplatform.library")
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)
            implementation(projects.core.storage.domain)
            implementation(projects.core.ui)

            implementation(libs.kotlin.datetime)
            implementation(libs.bundles.koin)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
        }
    }
}