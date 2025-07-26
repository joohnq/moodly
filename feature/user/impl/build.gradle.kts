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

            implementation(projects.core.database)

            implementation(projects.core.ui)

            implementation(projects.core.storage.api)

            implementation(projects.sharedResources)

            implementation(projects.feature.user.api)

            implementation(libs.serialization)
            implementation(libs.kotlin.datetime)
            implementation(libs.coroutines.extensions)
            implementation(libs.kotlin.datetime)

            implementation(libs.bundles.koin)
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
