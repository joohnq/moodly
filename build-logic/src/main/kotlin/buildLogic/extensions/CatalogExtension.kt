package buildLogic.extensions

import buildLogic.configs.AppConfig
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.use.PluginDependency

internal val Project.versionCatalog: VersionCatalog
    get() {
        return extensions.getByType<VersionCatalogsExtension>().named("libs")
    }

internal fun Project.getPlugin(alias: String): PluginDependency {
    return versionCatalog.findPlugin(alias).get().get()
}

internal fun Project.getLibrary(alias: String): MinimalExternalModuleDependency {
    return versionCatalog.findLibrary(alias).get().get()
}

fun Project.geDeriveNamespace(): String {
    val basePackage = rootProject.findProperty("basePackage") as? String
        ?: AppConfig.APPLICATION_NAME

    val modulePath = path.replaceFirst(":", "")
        .replace(":", ".")
        .replace("-", "")

    return "$basePackage$modulePath"
}