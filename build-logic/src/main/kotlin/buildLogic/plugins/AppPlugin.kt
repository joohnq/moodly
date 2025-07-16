package buildLogic.plugins

import buildLogic.configs.AppConfig
import buildLogic.extensions.getPlugin
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            installPlugins(this)
            androidSettings(this)
            configureKotlin(this)
        }
    }

    private fun installPlugins(target: Project) {
        target.pluginManager.apply(
            target.getPlugin(alias = "android-application").pluginId
        )
        target.pluginManager.apply(
            target.getPlugin(alias = "kotlin-android").pluginId
        )
    }

    private fun androidSettings(target: Project) {
        target.extensions.configure<BaseAppModuleExtension> {
            namespace = AppConfig.appId

            compileSdk = AppConfig.compileSdk

            defaultConfig {
                applicationId = AppConfig.appId
                minSdk = AppConfig.minSdk
                targetSdk = AppConfig.targetSdk
                versionCode = AppConfig.versionCode
                versionName = AppConfig.versionName
            }

            buildTypes {
                release {
                    isMinifyEnabled = true
                    isShrinkResources = true
                }

                debug {

                }
            }

            compileOptions {
                sourceCompatibility = AppConfig.javaVersion
                targetCompatibility = AppConfig.javaVersion
            }
        }
    }

    private fun configureKotlin(target: Project) {
        target.tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = AppConfig.jvmTarget
            }
        }
    }
}