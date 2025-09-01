package buildLogic.plugins

import buildLogic.configs.AppConfig
import buildLogic.extensions.configureDetekt
import buildLogic.extensions.getDeriveNamespace
import buildLogic.extensions.getLibrary
import buildLogic.extensions.getPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            installPlugins()
            configureKotlinMultiplatform()
            configureDetektPlugin()
        }
    }

    private fun Project.installPlugins() {
        pluginManager.apply(
            getPlugin("kotlin-multiplatform").pluginId
        )
        pluginManager.apply(
            getPlugin(alias = "detekt").pluginId
        )
    }

    private fun Project.configureKotlinMultiplatform() {
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

            jvm()

            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64()
            ).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    var deriveBaseName = getDeriveNamespace()

                    if (deriveBaseName == "${AppConfig.APPLICATION_NAME}composeApp") {
                        deriveBaseName = "ComposeApp"
                    }

                    baseName = deriveBaseName
                    isStatic = true
                    linkerOpts.add("-lsqlite3")
                }
            }

            compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }

            sourceSets {
                jvmMain.dependencies {
                    implementation(getLibrary("coroutines-swing"))
//                    implementation(getLibrary("compose-desktop-macos"))
                }
            }
        }
    }

    private fun Project.configureDetektPlugin() {
        configureDetekt()
    }
}