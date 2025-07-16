plugins {
    id("moodly.multiplatform.library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.splash.ui)
            implementation(projects.sharedResources)
            implementation(projects.feature.freudScore.ui)
            implementation(projects.feature.freudScore.domain)
            implementation(projects.feature.mood.ui)
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.selfJournal.domain)
            implementation(projects.feature.stressLevel.ui)
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.user.ui)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.bundles.viewmodel)
            implementation(libs.bundles.koin)

            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
            implementation(libs.turbine)
            implementation(libs.napier)
        }
    }
}