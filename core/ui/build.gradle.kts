plugins {
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.kotlin.datetime)
            implementation(libs.bundles.koin)
            implementation(libs.calendar)

            implementation(libs.coroutines.core)
            implementation(libs.coroutines.extensions)

            implementation(libs.navigation.compose)
        }
    }
}