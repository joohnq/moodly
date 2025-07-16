package buildLogic.plugins

import buildLogic.configs.AppConfig
import buildLogic.extensions.geDeriveNamespace
import buildLogic.extensions.getPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            installPlugins(this)
            configureKotlinMultiplatform(this)
        }
    }

    private fun installPlugins(target: Project) {
        target.pluginManager.apply(
            target.getPlugin(alias = "kotlin-multiplatform").pluginId
        )
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

            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64()
            ).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    baseName = geDeriveNamespace()
                    isStatic = true
                    linkerOpts.add("-lsqlite3")
                }
            }

            compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
}