plugins {
    id("moodly.multiplatform.library")
    alias(libs.plugins.serialization)
}

android {
    namespace = "com.joohnq.domain"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.datetime)
        }
    }
}