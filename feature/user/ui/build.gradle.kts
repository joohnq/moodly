plugins {
    id("moodly.multiplatform.library")
    id("moodly.compose")
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.sharedResources)
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.user.domain)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.kotlin.datetime)

            implementation(libs.bundles.koin)
        }
        commonTest.dependencies {
            implementation(projects.core.storage.domain)
            implementation(projects.core.test)
            implementation(libs.bundles.test)
            implementation(libs.turbine)
        }
    }
}