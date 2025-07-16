plugins {
    id("moodly.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.cryptography.domain)
            implementation(libs.cryptography.core)
            implementation(libs.bundles.koin)
        }
    }
}