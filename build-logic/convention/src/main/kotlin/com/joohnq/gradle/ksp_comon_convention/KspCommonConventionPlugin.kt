package com.joohnq.gradle.ksp_comon_convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class KspCommonConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
//            extensions.configure(
//                "kotlin",
//                KotlinMultiplatformExtension::class.java
//            ) {
//                sourceSets.named("commonMain").configure {
//                    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
//                }
//            }

//            tasks.register("kotlinCompilationTask", Exec::class.java) {
//                description = "kspCommonMainKotlinMetadata"
//                group = "koin ksp"
//                workingDir = rootDir
////                commandLine = listOf("chmod")
////                args("-R", "+x", ".git/hooks/")
//                if (name != "kspCommonMainKotlinMetadata") {
//                    dependsOn("kspCommonMainKotlinMetadata")
//                }
//            }
//            // Configura dependências comuns do KSP
//            tasks.withType(KotlinCompilationTask::class.java).configureEach {
//                if (name != "kspCommonMainKotlinMetadata") {
//                    dependsOn("kspCommonMainKotlinMetadata")
//                }
//            }

            val koinKsp = versionCatalog("koin.ksp")
            dependencies {
                add("kspCommonMainMetadata", koinKsp)
                add("kspAndroid", koinKsp)
                add("kspIosX64", koinKsp)
                add("kspIosArm64", koinKsp)
                add("kspIosSimulatorArm64", koinKsp)
            }
        }
    }
}

private fun Project.versionCatalog(alias: String): Provider<MinimalExternalModuleDependency> {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    return libs.findLibrary(alias).get()
}