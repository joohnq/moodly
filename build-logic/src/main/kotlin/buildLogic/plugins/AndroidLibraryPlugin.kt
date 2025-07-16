package buildLogic.plugins

import buildLogic.extensions.getPlugin
import buildLogic.plugins.android.LibraryAndroidSettings
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            installPlugins()
            androidSettings()
        }
    }

    private fun Project.installPlugins() {
        pluginManager.apply(
            getPlugin("android-library").pluginId
        )
    }

    private fun Project.androidSettings() {
        LibraryAndroidSettings().setup(this)
    }
}