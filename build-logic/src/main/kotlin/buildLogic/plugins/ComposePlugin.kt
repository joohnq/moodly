package buildLogic.plugins

import buildLogic.configs.AppConfig
import buildLogic.extensions.debugImplementation
import buildLogic.extensions.getLibrary
import buildLogic.extensions.getPlugin
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposePlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        installPlugins()
        androidSettings()
        installDependencies()
        installDebugUiTooling()
    }

    private fun Project.installPlugins() {
        pluginManager.apply(
            getPlugin(alias = "jetbrains-compose").pluginId
        )
        pluginManager.apply(
            getPlugin(alias = "compose-compiler").pluginId
        )
    }

    private fun Project.androidSettings() {
        extensions.configure<LibraryExtension> {
            compileSdk = AppConfig.COMPILE_SDK

            buildFeatures {
                compose = true
            }
        }
    }

    private fun Project.installDependencies() {
        configure<KotlinMultiplatformExtension> {
            sourceSets {
                commonMain.dependencies {
                    implementation(getLibrary(alias = "compose-runtime"))
                    implementation(getLibrary(alias = "compose-foundation"))
                    implementation(getLibrary(alias = "compose-material3"))
                    implementation(getLibrary(alias = "compose-ui"))
                    implementation(getLibrary(alias = "compose-components-resources"))
                    implementation(getLibrary(alias = "compose-components-ui-tooling-preview"))
                }
            }
        }
    }

    private fun Project.installDebugUiTooling() {
        dependencies {
            debugImplementation(getLibrary("androidx-ui-tooling"))
        }
    }
}