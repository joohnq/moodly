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

            implementation(projects.core.storage.api)

            implementation(projects.feature.sleepQuality.api)

            implementation(libs.kotlin.datetime)
            implementation(libs.charts)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            implementation(libs.ktor.client.core)
            implementation(libs.calendar)
            implementation("dev.chrisbanes.haze:haze:1.6.9")
        }
        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.joohnq.shared_resources"
    generateResClass = auto
}
