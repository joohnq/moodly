import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqldelight)
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
            baseName = "stress_level.data"
            isStatic = true
            linkerOpts.add("-lsqlite3")
        }
    }

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

android {
    namespace = "com.joohnq.stress_level.data"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
