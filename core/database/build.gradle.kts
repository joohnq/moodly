import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
            baseName = "database"
            isStatic = true
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.android.driver)
        }
        commonMain.dependencies {
            implementation(libs.kotlin.datetime)

            implementation(libs.bundles.koin)
        }
        nativeMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
    }
}

android {
    namespace = "com.joohnq.database"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
