package buildLogic.plugins

import buildLogic.extensions.getPlugin
import buildLogic.plugins.android.LibraryAndroidSettings
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            installPlugins(this)
            androidSettings(this)
        }
    }

    private fun installPlugins(target: Project) {
        target.pluginManager.apply(
            target.getPlugin(alias = "android-library").pluginId
        )
    }

    private fun androidSettings(target: Project) {
        LibraryAndroidSettings().setup(target)
    }
}