package buildLogic.plugins

import buildLogic.configs.AppConfig
import buildLogic.extensions.getPlugin
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ComposePlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        installPlugins()
        androidSettings()
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
}