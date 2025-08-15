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
            setupSourceSets()

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
                isMinifyEnabled = true
                isShrinkResources = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                ndkVersion = "27.0.12077973"
                ndk.debugSymbolLevel = "FULL"
            }
        }
    }

    private fun BaseAppModuleExtension.setupSourceSets() {
        sourceSets.getByName("main").apply {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
            resources.srcDirs("src/commonMain/resources")
        }
        sourceSets.findByName("androidDebug")?.apply {
            java.srcDir("src/androidDebug/java")
            res.srcDir("src/androidDebug/res")
            manifest.srcFile("src/androidDebug/AndroidManifest.xml")
        }
    }
}