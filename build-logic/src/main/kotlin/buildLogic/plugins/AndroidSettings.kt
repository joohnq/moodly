package buildLogic.plugins

import buildLogic.configs.AppConfig
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
            compileSdk = AppConfig.COMPILE_SDK

            defaultConfig {
                minSdk = AppConfig.MIN_SDK
            }

            compileOptions {
                sourceCompatibility = AppConfig.javaVersion
                targetCompatibility = AppConfig.javaVersion
            }

            buildTypes {
                release {
                    isMinifyEnabled = false
                }
            }

            config?.invoke(this)
        }
    }

    fun setupBaseAppModuleExtension(
        target: Project,
        config: (BaseAppModuleExtension.() -> Unit)? = null
    ){
        target.extensions.configure<BaseAppModuleExtension> {
            compileSdk = AppConfig.COMPILE_SDK

            defaultConfig {
                minSdk = AppConfig.MIN_SDK
            }

            compileOptions {
                sourceCompatibility = AppConfig.javaVersion
                targetCompatibility = AppConfig.javaVersion
            }

            buildTypes {
                release {
                    isMinifyEnabled = false
                    isShrinkResources = true
                }
            }

            config?.invoke(this)
        }
    }
}