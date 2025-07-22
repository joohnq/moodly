plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.storage.api)

            implementation(projects.core.domain)

            implementation(libs.squareup.okio)
            implementation(libs.bundles.koin)
        }
    }
}