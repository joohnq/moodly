package buildLogic.plugins

import buildLogic.configs.AppConfig
import buildLogic.extensions.geDeriveNamespace
import buildLogic.extensions.getPlugin
import buildLogic.plugins.android.AppAndroidSettings
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            installPlugins()
            androidSettings()
            configureKotlin()
        }
    }

    private fun Project.installPlugins() {
        pluginManager.apply(
            getPlugin(alias = "android-application").pluginId
        )
    }

    private fun Project.androidSettings() {
        AppAndroidSettings().setup(this){
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            ndkVersion = "27.0.12077973"
        }
    }

    private fun Project.configureKotlin() {
        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = AppConfig.JVM_TARGET
            }
        }
    }
}