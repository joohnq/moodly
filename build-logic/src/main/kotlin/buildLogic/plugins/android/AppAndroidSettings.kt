package buildLogic.plugins.android

import buildLogic.configs.AppConfig
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppAndroidSettings : AndroidSettings<BaseAppModuleExtension>() {
    override fun setup(
        target: Project,
        config: (BaseAppModuleExtension.() -> Unit)?
    ) {
        target.extensions.configure<BaseAppModuleExtension> {
            setupCommon(target)
            setupDefaultConfig()
            setupCompileOptions()
            setupBuildTypes()

            config?.invoke(this)
        }
    }

    override fun BaseAppModuleExtension.setupDefaultConfig() {
        defaultConfig {
            applicationId = AppConfig.APPLICATION_ID
            minSdk = AppConfig.MIN_SDK
            targetSdk = AppConfig.TARGET_SDK
            versionCode = AppConfig.VERSION_CODE
            versionName = AppConfig.VERSION_NAME
        }
    }

    override fun BaseAppModuleExtension.setupBuildTypes() {
        buildTypes {
            release {
                isMinifyEnabled = false
                isShrinkResources = true
            }
        }
    }
}
