import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
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
            implementation(projects.core.ui)
            implementation(projects.shared.ui)
            implementation(projects.shared.domain)
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.freudScore.domain)
            implementation(projects.feature.mood.ui)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.bundles.koin)
            implementation(libs.datetime)
            implementation(libs.bundles.voyager)
            implementation(libs.bundles.voyager.other)
        }
    }
}

ksp {
    arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
}



dependencies {
    add("kspCommonMainMetadata", libs.koin.ksp)
    add("kspAndroid", libs.koin.ksp)
    add("kspIosX64", libs.koin.ksp)
    add("kspIosArm64", libs.koin.ksp)
    add("kspIosSimulatorArm64", libs.koin.ksp)
}

android {
    namespace = "com.joohnq.freud_score.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
