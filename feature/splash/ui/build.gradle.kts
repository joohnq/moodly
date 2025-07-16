plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.sharedResources)
            implementation(projects.feature.preferences.domain)
            implementation(projects.feature.preferences.ui)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.user.ui)
            implementation(projects.feature.security.ui)
            implementation(projects.feature.security.domain)
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.core.navigation)

            implementation(libs.navigation.compose)

            implementation(libs.bundles.viewmodel)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}