package buildLogic.extensions

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.debugImplementation(library: MinimalExternalModuleDependency){
    add("debugImplementation", library)
}