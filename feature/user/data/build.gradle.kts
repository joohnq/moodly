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
            baseName = "user.data"
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
            implementation(projects.core.domain)
            implementation(projects.feature.user.domain)
            implementation(projects.core.database)
            implementation(projects.core.storage.domain)

            implementation(libs.serialization)
            implementation(libs.bundles.koin)

            implementation(libs.kotlin.datetime)

            implementation(libs.coroutines.extensions)
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

android {
    namespace = "com.joohnq.user.data"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
