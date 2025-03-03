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
            baseName = "test"
            isStatic = true
        }
    }

    sourceSets {
        androidUnitTest.dependencies {
            implementation(libs.android.driver)
        }
        commonMain.dependencies {
            implementation(libs.kotlin.datetime)
            implementation(projects.core.database)
            implementation(libs.bundles.test)
            implementation(libs.coroutines.extensions)
            implementation(libs.turbine)
        }
        commonTest.dependencies {
            implementation(projects.core.database)
            implementation(libs.bundles.test)
            implementation(libs.coroutines.extensions)
        }
        nativeTest.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
    }
}

android {
    namespace = "com.joohnq.test"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}