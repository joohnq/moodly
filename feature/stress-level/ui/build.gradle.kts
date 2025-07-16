import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("moodly.multiplatform.library")
    id("moodly.compose")
    alias(libs.plugins.serialization)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.sharedResources)
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.splash.ui)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.bundles.viewmodel)
            implementation(libs.serialization)

            implementation(libs.bundles.koin)
            implementation(libs.charts)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
            implementation(libs.turbine)
        }
    }
}