plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)

            implementation(projects.core.domain)

            implementation(projects.core.navigation)

            implementation(projects.sharedResources)

            implementation(projects.feature.preferences.api)
            implementation(projects.feature.preferences.impl)

            implementation(projects.feature.user.api)
            implementation(projects.feature.user.impl)

            implementation(projects.feature.security.api)
            implementation(projects.feature.security.impl)

            implementation(libs.navigation.compose)

            implementation(libs.bundles.viewmodel)
        }
    }
}