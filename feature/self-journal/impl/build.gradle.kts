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
            implementation(projects.core.domain)
            implementation(projects.core.database)
            implementation(projects.feature.selfJournal.api)
            implementation(projects.feature.mood.api)

            implementation(libs.kotlin.datetime)

            implementation(libs.bundles.koin)
            implementation(libs.coroutines.extensions)

            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.feature.splash.impl)
            implementation(projects.sharedResources)
            implementation(projects.feature.freudScore.impl)
            implementation(projects.feature.freudScore.api)
            implementation(projects.feature.mood.impl)
            implementation(projects.feature.mood.api)
            implementation(projects.feature.selfJournal.api)
            implementation(projects.feature.stressLevel.ui)
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.user.ui)

            implementation(libs.bundles.viewmodel)
            implementation(libs.bundles.koin)

            implementation(libs.kotlin.datetime)
            implementation(libs.calendar)
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