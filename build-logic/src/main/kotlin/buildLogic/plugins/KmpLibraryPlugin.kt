package buildLogic.plugins

import buildLogic.configs.AppConfig
import buildLogic.extensions.getPlugin
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            installPlugins(this)
            androidSettings(this)
            configureKotlinMultiplatform(this)
        }
    }

    private fun installPlugins(target: Project) {
        target.pluginManager.apply(
            target.getPlugin(alias = "android-library").pluginId
        )
        target.pluginManager.apply(
            target.getPlugin(alias = "kotlin-multiplatform").pluginId
        )
        target.pluginManager.apply(
            target.getPlugin(alias = "detekt").pluginId
        )
    }

    private fun androidSettings(target: Project) {
        target.extensions.configure<LibraryExtension> {
            compileSdk = AppConfig.COMPILE_SDK

            defaultConfig {
                minSdk = AppConfig.MIN_SDK
            }

            compileOptions {
                sourceCompatibility = AppConfig.javaVersion
                targetCompatibility = AppConfig.javaVersion
            }

            buildTypes {
                release {
                    isMinifyEnabled = false
                }
            }
        }
    }

    private fun configureKotlinMultiplatform(target: Project) = with(target) {
        configure<KotlinMultiplatformExtension> {
            androidTarget {
                compilations.all {
                    compileTaskProvider.configure {
                        compilerOptions {
                            jvmTarget.set(AppConfig.jvmTargetVersion)
                        }
                    }
                }
            }

            iosX64()
            iosArm64()
            iosSimulatorArm64()

            compilerOptions {
                // Common compiler options applied to all Kotlin source sets
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
}