plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.startup.runtime)
            implementation(libs.android.driver)
        }
        commonMain.dependencies {
            implementation(projects.core.database)
            implementation(projects.core.domain)
            implementation(projects.feature.mood.api)
            implementation(projects.feature.user.impl)
            implementation(projects.feature.user.api)
            implementation(projects.feature.sleepQuality.api)
            implementation(projects.feature.freudScore.api)
            implementation(projects.sharedResources)
            implementation(projects.core.ui)

            implementation(libs.coroutines.extensions)
            implementation(libs.bundles.viewmodel)
            implementation(libs.serialization)
            implementation(libs.bundles.koin)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)
        }
        commonTest.dependencies {
            implementation(projects.core.test)
            implementation(libs.bundles.test)
        }
        iosMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
        nativeMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
    }
}

sqldelight {
    databases {
        create("MoodDatabaseSql") {
            packageName.set("com.joohnq.mood.database")
        }
    }
}