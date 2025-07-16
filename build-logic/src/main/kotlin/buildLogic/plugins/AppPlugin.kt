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
        AndroidSettings.setupBaseAppModuleExtension(target){
            namespace = AppConfig.APPLICATION_ID

            defaultConfig {
                applicationId = AppConfig.APPLICATION_ID
                minSdk = AppConfig.MIN_SDK
                targetSdk = AppConfig.TARGET_SDK
                versionCode = AppConfig.VERSION_CODE
                versionName = AppConfig.VERSION_NAME
            }
        }
    }

    private fun configureKotlin(target: Project) {
        target.tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = AppConfig.JVM_TARGET
            }
        }
    }
}