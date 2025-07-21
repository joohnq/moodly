plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.core.storage.api)

            implementation(libs.kotlin.datetime)
            implementation(libs.charts)

            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            implementation(libs.ktor.client.core)
            implementation(libs.calendar)
        }
        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

dependencies {
    debugImplementation(libs.androidx.ui.tooling)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.joohnq.shared_resources"
    generateResClass = auto
}