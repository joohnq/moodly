import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.mokkery)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "self_journal.data"
            isStatic = true
            linkerOpts.add("-lsqlite3")
        }
    }

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

android {
    namespace = "com.joohnq.self_journal.data"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

