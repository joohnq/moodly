plugins {
    id("moodly.multiplatform.library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.navigation)
            implementation(projects.feature.stressLevel.ui)
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.feature.sleepQuality.ui)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.feature.freudScore.ui)
            implementation(projects.feature.freudScore.domain)
            implementation(projects.feature.selfJournal.ui)
            implementation(projects.feature.selfJournal.domain)
            implementation(projects.feature.user.ui)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.mood.ui)
            implementation(projects.feature.mood.domain)
            implementation(projects.sharedResources)
            implementation(projects.feature.splash.ui)
            implementation(projects.core.ui)
            implementation(projects.core.domain)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.navigation.compose)

            implementation(libs.kotlin.datetime)
            implementation(libs.bundles.koin)
            implementation(libs.calendar)
        }
    }
}