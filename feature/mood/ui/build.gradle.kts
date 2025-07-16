plugins {
    id("moodly.multiplatform.library")
    id("moodly.compose")
    alias(libs.plugins.serialization)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.user.ui)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.feature.freudScore.domain)
            implementation(projects.sharedResources)
            implementation(projects.core.ui)
            implementation(projects.core.domain)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.bundles.viewmodel)
            implementation(libs.serialization)
            implementation(libs.bundles.koin)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
            implementation(libs.turbine)
        }
    }
}

dependencies {
    debugImplementation(libs.androidx.ui.tooling)
}