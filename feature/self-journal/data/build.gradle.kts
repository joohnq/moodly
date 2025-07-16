plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)
            implementation(projects.core.database)
            implementation(projects.feature.selfJournal.domain)
            implementation(projects.feature.mood.domain)

            implementation(libs.kotlin.datetime)

            implementation(libs.bundles.koin)
            implementation(libs.coroutines.extensions)
        }
        commonTest.dependencies {
            implementation(projects.core.test)
            implementation(libs.bundles.test)
        }
        androidMain.dependencies {
            implementation(libs.androidx.startup.runtime)
            implementation(libs.android.driver)
        }
        nativeMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
    }
}

sqldelight {
    databases {
        create("SelfJournalDatabaseSql") {
            packageName.set("com.joohnq.self_journal.database")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/schema/self_journal"))
            migrationOutputDirectory = file("src/commonMain/sqldelight/migrations/self_journal")
        }
    }
}