import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
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
            baseName = "home.ui"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.navigation)
            implementation(projects.feature.stressLevel.ui)
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.feature.sleepQuality.ui)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.feature.freudScore.ui)
            implementation(projects.feature.freudScore.domain)
            implementation(projects.feature.selfJournal.ui)
            implementation(projects.feature.selfJournal.domain)
            implementation(projects.feature.user.ui)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.mood.ui)
            implementation(projects.feature.mood.domain)
            implementation(projects.sharedResources)
            implementation(projects.feature.splash.ui)
            implementation(projects.core.ui)
            implementation(projects.core.domain)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.navigation.compose)

            implementation(libs.kotlin.datetime)
            implementation(libs.bundles.koin)
            implementation(libs.calendar)
        }
    }
}

android {
    namespace = "com.joohnq.home.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
