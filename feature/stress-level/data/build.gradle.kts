plugins {
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
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.core.domain)

            implementation(libs.kotlin.datetime)

            implementation(libs.serialization)
            implementation(libs.bundles.koin)

            implementation(libs.coroutines.extensions)
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