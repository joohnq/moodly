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
            implementation(projects.core.storage.data)
            implementation(projects.sharedResources)
            implementation(projects.feature.security.data)
            implementation(projects.feature.security.ui)
            implementation(projects.feature.security.domain)

            implementation(projects.feature.auth.ui)

            implementation(projects.feature.preferences.domain)
            implementation(projects.feature.preferences.data)
            implementation(projects.feature.preferences.ui)

            implementation(projects.feature.welcome.ui)
            implementation(projects.feature.freudScore.ui)
            implementation(projects.feature.onboarding.ui)

            implementation(projects.feature.home.ui)

            implementation(projects.feature.user.data)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.user.ui)

            implementation(projects.feature.mood.ui)
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.mood.data)

            implementation(projects.feature.selfJournal.ui)
            implementation(projects.feature.selfJournal.domain)
            implementation(projects.feature.selfJournal.data)

            implementation(projects.feature.sleepQuality.ui)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.feature.sleepQuality.data)

            implementation(projects.feature.stressLevel.ui)
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.feature.stressLevel.data)

            implementation(projects.feature.splash.ui)

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
