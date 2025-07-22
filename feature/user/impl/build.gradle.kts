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
            implementation(projects.core.domain)
            implementation(projects.feature.user.api)
            implementation(projects.core.database)
            implementation(projects.core.storage.api)

            implementation(libs.serialization)
            implementation(libs.bundles.koin)

            implementation(libs.kotlin.datetime)

            implementation(libs.coroutines.extensions)

            implementation(projects.sharedResources)
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.user.api)

            implementation(libs.kotlin.datetime)

            implementation(libs.bundles.koin)
        }
        commonTest.dependencies {
            implementation(projects.core.test)
            implementation(libs.bundles.test)
        }
        nativeMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
    }
}

sqldelight {
    databases {
        create("UserDatabaseSql") {
            packageName.set("com.joohnq.user.database")
        }
    }
}