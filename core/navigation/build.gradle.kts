plugins {
    id("moodly.multiplatform.library")
    alias(libs.plugins.serialization)
}

android {
    namespace = "com.joohnq.navigation"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.serialization)
            implementation(libs.navigation.compose)
            implementation(libs.kotlin.datetime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}