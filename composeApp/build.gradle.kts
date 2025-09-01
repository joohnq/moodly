import buildLogic.configs.AppConfig
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import kotlin.text.set

plugins {
    id("moodly.application")
    id("moodly.multiplatform.library")
    id("moodly.compose")

    alias(libs.plugins.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.composeHotReload)
//    alias(libs.plugins.crashlytics)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

//            implementation(libs.gitlive.firebase.kotlin.crashlytics)
        }
        commonMain.dependencies {
            implementation(projects.core.database)

            implementation(projects.core.datastore)

            implementation(projects.core.navigation)

            implementation(projects.core.ui)

            implementation(projects.core.domain)

            implementation(projects.core.storage.impl)

            implementation(projects.sharedResources)

            implementation(projects.feature.security.impl)
            implementation(projects.feature.security.api)

            implementation(projects.feature.auth.impl)

            implementation(projects.feature.preferences.api)
            implementation(projects.feature.preferences.impl)

            implementation(projects.feature.welcome.impl)

            implementation(projects.feature.freudScore.impl)

            implementation(projects.feature.onboarding.impl)

            implementation(projects.feature.home.impl)

            implementation(projects.feature.user.api)
            implementation(projects.feature.user.impl)

            implementation(projects.feature.mood.api)
            implementation(projects.feature.mood.add)
            implementation(projects.feature.mood.impl)
            implementation(projects.feature.mood.history)
            implementation(projects.feature.mood.overview)

            implementation(projects.feature.selfJournal.api)
            implementation(projects.feature.selfJournal.add)
            implementation(projects.feature.selfJournal.edit)
            implementation(projects.feature.selfJournal.history)
            implementation(projects.feature.selfJournal.impl)
            implementation(projects.feature.selfJournal.overview)

            implementation(projects.feature.sleepQuality.add)
            implementation(projects.feature.sleepQuality.api)
            implementation(projects.feature.sleepQuality.history)
            implementation(projects.feature.sleepQuality.impl)
            implementation(projects.feature.sleepQuality.overview)

            implementation(projects.feature.stressLevel.add)
            implementation(projects.feature.stressLevel.api)
            implementation(projects.feature.stressLevel.history)
            implementation(projects.feature.stressLevel.impl)
            implementation(projects.feature.stressLevel.overview)

            implementation(projects.feature.gratefulness.add)
            implementation(projects.feature.gratefulness.api)
            implementation(projects.feature.gratefulness.impl)
            implementation(projects.feature.gratefulness.overview)
            implementation(projects.feature.gratefulness.history)

            implementation(projects.feature.splash.impl)

            implementation(libs.kotlin.datetime)
            implementation(libs.compose.navigation)

            implementation(libs.bundles.koin)
        }
    }
}

android {
    base {
        archivesName = "Moodly-v${AppConfig.VERSION_CODE}"
    }
}

compose.desktop {
    application {
        mainClass = "${AppConfig.APPLICATION_ID}.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = AppConfig.APPLICATION_ID
            packageVersion = AppConfig.VERSION_NAME
        }
    }
}
