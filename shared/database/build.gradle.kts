import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "database"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting
        commonMain.dependencies {
            implementation(projects.feature.user.domain)
            implementation(projects.feature.user.data)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.feature.sleepQuality.data)
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.feature.stressLevel.data)
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.mood.data)
            implementation(projects.feature.healthJournal.domain)
            implementation(projects.feature.healthJournal.data)

            implementation(libs.room.runtime)
        }
    }
}

android {
    namespace = "com.joohhq.shared.database"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.desktop {
    application {
        mainClass = "com.joohnq.shared.database.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.joohnq.shared.database"
            packageVersion = "1.0.0"
        }
    }
}
