package buildLogic.plugins

import buildLogic.configs.AppConfig
import buildLogic.extensions.geDeriveNamespace
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.api.Project

object AndroidSettings {
    fun setupLibraryExtension(
        target: Project,
        config: (LibraryExtension.() -> Unit)? = null
    ) {
        target.extensions.configure<LibraryExtension> {
            setupConfig(target)
            setupLibraryDefaultConfig()
            setupCompileOptions()
            setupLibraryBuildTypes()

            config?.invoke(this)
        }
    }

    fun setupBaseAppModuleExtension(
        target: Project,
        config: (BaseAppModuleExtension.() -> Unit)? = null
    ){
        target.extensions.configure<BaseAppModuleExtension> {
            setupConfig(target)
            setupBaseAppDefaultConfig()
            setupCompileOptions()
            setupBaseAppBuildTypes()

            config?.invoke(this)
        }
    }

    private fun BaseAppModuleExtension.setupConfig(target: Project) {
        namespace = target.geDeriveNamespace()
        compileSdk = AppConfig.COMPILE_SDK
    }

    private fun BaseAppModuleExtension.setupLibraryDefaultConfig() {
        defaultConfig {
            minSdk = AppConfig.MIN_SDK
        }
    }

    private fun BaseAppModuleExtension.setupBaseAppDefaultConfig() {
        defaultConfig {
            applicationId = AppConfig.APPLICATION_ID
            minSdk = AppConfig.MIN_SDK
            targetSdk = AppConfig.TARGET_SDK
            versionCode = AppConfig.VERSION_CODE
            versionName = AppConfig.VERSION_NAME
        }
    }

    private fun BaseAppModuleExtension.setupCompileOptions() {
        compileOptions {
            sourceCompatibility = AppConfig.javaVersion
            targetCompatibility = AppConfig.javaVersion
        }
    }

    private fun BaseAppModuleExtension.setupLibraryBuildTypes() {
        buildTypes {
            release {
                isMinifyEnabled = false
            }
        }
    }

    private fun BaseAppModuleExtension.setupBaseAppBuildTypes() {
        buildTypes {
            release {
                isMinifyEnabled = false
                isShrinkResources = true
            }
        }
    }
}