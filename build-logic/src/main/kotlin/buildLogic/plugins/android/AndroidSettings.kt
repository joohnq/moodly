package buildLogic.plugins.android

import buildLogic.configs.AppConfig
import buildLogic.extensions.geDeriveNamespace
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project

abstract class AndroidSettings<CONFIG> {
    abstract fun setup(target: Project, config: (CONFIG.() -> Unit)? = null)
    abstract fun CONFIG.setupDefaultConfig()
    abstract fun CONFIG.setupBuildTypes()

    protected fun CONFIG.setupCommon(target: Project) {
        when (this) {
            is BaseAppModuleExtension -> {
                namespace = target.geDeriveNamespace()
                compileSdk = AppConfig.COMPILE_SDK
            }

            is LibraryExtension -> {
                namespace = target.geDeriveNamespace()
                compileSdk = AppConfig.COMPILE_SDK
            }

            else -> throw NotImplementedError("Unsupported extension type")
        }
    }

    protected fun CONFIG.setupCompileOptions() {
        when (this) {
            is BaseAppModuleExtension -> {
                compileOptions {
                    sourceCompatibility = AppConfig.javaVersion
                    targetCompatibility = AppConfig.javaVersion
                }
            }

            is LibraryExtension -> {
                compileOptions {
                    sourceCompatibility = AppConfig.javaVersion
                    targetCompatibility = AppConfig.javaVersion
                }
            }

            else -> throw NotImplementedError("Unsupported extension type")
        }
    }
}