import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    //Prevent the error: The same 'unique_name=runtime_commonMain'
//    metadata {
//        compilations.all {
//            val compilationName = name
//            compileTaskProvider.configure {
//                if (this is KotlinCompileCommon) {
//                    moduleName = "${project.group}:${project.name}_$compilationName"
//                }
//            }
//        }
//    }

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
            implementation(projects.feature.freudScore.domain)
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.feature.healthJournal.domain)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.shared.domain)
            implementation(projects.feature.mood.domain)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.datetime)

            // Koin
            implementation(libs.bundles.koin)

            implementation(libs.bundles.voyager)
            implementation(libs.bundles.voyager.other)
        }
    }
}

android {
    namespace = "com.joohnq.shared.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.joohnq.shared.ui"
    generateResClass = auto
}
