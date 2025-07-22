plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")
    
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.mokkery)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.database)

            implementation(projects.core.ui)

            implementation(projects.core.domain)

            implementation(projects.sharedResources)

            implementation(projects.feature.splash.impl)

            implementation(projects.feature.freudScore.api)
            implementation(projects.feature.freudScore.impl)

            implementation(projects.feature.mood.api)
            implementation(projects.feature.mood.impl)

            implementation(projects.feature.selfJournal.api)
            implementation(projects.feature.stressLevel.impl)

            implementation(projects.feature.stressLevel.api)

            implementation(projects.feature.user.api)
            implementation(projects.feature.user.impl)

            implementation(libs.coroutines.extensions)
            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)

            implementation(libs.bundles.viewmodel)
            implementation(libs.bundles.koin)
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