plugins {
    id("moodly.application")
    id("moodly.multiplatform.library")
    id("moodly.compose")
    alias(libs.plugins.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.crashlytics)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.firebase.android.bom)

            implementation(libs.firebase.crashlytics)
            implementation(libs.firebase.analytics)
        }
        commonMain.dependencies {
            api(libs.gitlive.firebase.kotlin.crashlytics)
            implementation(projects.core.datastore)
            implementation(projects.core.navigation)
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.core.storage.impl)
            implementation(projects.sharedResources)
            implementation(projects.feature.security.impl)
            implementation(projects.feature.security.api)

            implementation(projects.feature.auth.ui)

            implementation(projects.feature.preferences.api)
            implementation(projects.feature.preferences.impl)

            implementation(projects.feature.welcome.ui)
            implementation(projects.feature.freudScore.impl)
            implementation(projects.feature.onboarding.impl)

            implementation(projects.feature.home.impl)

            implementation(projects.feature.user.data)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.user.ui)

            implementation(projects.feature.mood.api)
            implementation(projects.feature.mood.impl)

            implementation(projects.feature.selfJournal.impl)
            implementation(projects.feature.selfJournal.api)

            implementation(projects.feature.sleepQuality.impl)
            implementation(projects.feature.sleepQuality.api)

            implementation(projects.feature.stressLevel.impl)
            implementation(projects.feature.stressLevel.api)

            implementation(projects.feature.splash.impl)

            implementation(libs.kotlin.datetime)
            implementation(libs.navigation.compose)

            implementation(libs.bundles.koin)
        }
    }
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    base {
        archivesName = "Moodly-v${defaultConfig.versionCode}"
    }
}
