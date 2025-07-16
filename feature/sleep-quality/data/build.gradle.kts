plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
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
            implementation(projects.feature.sleepQuality.domain)

            implementation(libs.kotlin.datetime)

            implementation(libs.serialization)
            implementation(libs.bundles.koin)

            implementation(libs.coroutines.extensions)
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
        create("SleepQualityDatabaseSql") {
            packageName.set("com.joohnq.sleep_quality.database")
        }
    }
}