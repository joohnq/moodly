package buildLogic.plugins

import buildLogic.extensions.getPlugin
import buildLogic.plugins.android.AppAndroidSettings
import org.gradle.api.Plugin
import org.gradle.api.Project

class AppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            installPlugins()
            androidSettings()
        }
    }

    private fun Project.installPlugins() {
        pluginManager.apply(
            getPlugin("android-application").pluginId
        )
    }

    private fun Project.androidSettings() {
        AppAndroidSettings().setup(this) {
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            ndkVersion = "27.0.12077973"
        }
    }
}