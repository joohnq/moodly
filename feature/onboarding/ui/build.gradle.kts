import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    //    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
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
            baseName = "ui"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.ui)
            implementation(projects.shared.domain)
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.mood.ui)
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.feature.stressLevel.ui)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.feature.sleepQuality.ui)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.user.ui)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.datetime)
            implementation(libs.bundles.viewmodel)
            implementation(libs.bundles.koin)
            implementation(libs.bundles.voyager)
            implementation(libs.bundles.voyager.other)
        }
    }
}

android {
    namespace = "com.joohnq.onboarding.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
