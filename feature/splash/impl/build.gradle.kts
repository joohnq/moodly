plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")

    alias(libs.plugins.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.database)

            implementation(projects.core.ui)

            implementation(projects.core.domain)

            implementation(projects.core.navigation)

            implementation(projects.core.storage.api)
            implementation(projects.core.storage.impl)

            implementation(projects.sharedResources)

            implementation(projects.feature.preferences.api)

            implementation(projects.feature.user.api)
            implementation(projects.feature.user.impl)

            implementation(projects.feature.security.api)
            implementation(projects.feature.security.impl)

            implementation(projects.feature.mood.api)
            implementation(projects.feature.mood.impl)

            implementation(projects.feature.selfJournal.api)
            implementation(projects.feature.selfJournal.impl)

            implementation(projects.feature.sleepQuality.api)
            implementation(projects.feature.sleepQuality.impl)

            implementation(projects.feature.stressLevel.api)
            implementation(projects.feature.stressLevel.impl)

            implementation(projects.feature.gratefulness.api)
            implementation(projects.feature.gratefulness.impl)

            implementation(libs.compose.navigation)

            implementation(libs.serialization)
            implementation(libs.kotlin.datetime)
            implementation(libs.bundles.viewmodel)
            implementation(libs.bundles.koin)
        }
    }
}
