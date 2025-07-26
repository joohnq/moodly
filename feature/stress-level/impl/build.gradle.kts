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

            implementation(projects.core.ui)

            implementation(projects.sharedResources)

            implementation(projects.feature.stressLevel.api)

            implementation(projects.feature.mood.api)

            implementation(projects.feature.splash.impl)

            implementation(libs.coroutines.extensions)
            implementation(libs.serialization)
            implementation(libs.charts)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)

            implementation(libs.bundles.koin)
            implementation(libs.bundles.viewmodel)
        }
        nativeMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
    }
}

sqldelight {
    databases {
        create("StressLevelDatabaseSql") {
            packageName.set("com.joohnq.stress_level.database")
        }
    }
}
