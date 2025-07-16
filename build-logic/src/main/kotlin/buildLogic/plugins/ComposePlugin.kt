package buildLogic.plugins

import buildLogic.configs.AppConfig
import buildLogic.extensions.debugImplementation
import buildLogic.extensions.getLibrary
import buildLogic.extensions.getPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
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
            getPlugin("jetbrains-compose").pluginId
        )
        pluginManager.apply(
            getPlugin("compose-compiler").pluginId
        )
    }

    private fun Project.androidSettings() {
        extensions.findByType(LibraryExtension::class.java)?.let { android ->
            android.compileSdk = AppConfig.COMPILE_SDK
            android.buildFeatures {
                compose = true
            }
        } ?: extensions.findByType(BaseAppModuleExtension::class.java)?.let { android ->
            android.compileSdk = AppConfig.COMPILE_SDK
            android.buildFeatures {
                compose = true
            }
        }
    }

    private fun Project.installDependencies() {
        configure<KotlinMultiplatformExtension> {
            sourceSets {
                commonMain.dependencies {
                    implementation(getLibrary("compose-runtime"))
                    implementation(getLibrary("compose-foundation"))
                    implementation(getLibrary("compose-material3"))
                    implementation(getLibrary("compose-ui"))
                    implementation(getLibrary("compose-components-resources"))
                    implementation(getLibrary("compose-components-ui-tooling-preview"))
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