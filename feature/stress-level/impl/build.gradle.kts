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
            implementation(projects.feature.stressLevel.api)
            implementation(projects.core.domain)

            implementation(libs.kotlin.datetime)

            implementation(libs.serialization)
            implementation(libs.bundles.koin)

            implementation(libs.coroutines.extensions)

            implementation(projects.feature.stressLevel.api)
            implementation(projects.sharedResources)
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.mood.api)
            implementation(projects.feature.splash.impl)

            implementation(libs.bundles.viewmodel)
            implementation(libs.serialization)

            implementation(libs.bundles.koin)
            implementation(libs.charts)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
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