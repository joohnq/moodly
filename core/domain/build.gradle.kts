plugins {
    id("moodly.multiplatform.library")
    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.datetime)
        }
    }
}