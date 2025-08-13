package buildLogic.plugins.android

import buildLogic.configs.AppConfig
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryAndroidSettings : AndroidSettings<LibraryExtension>() {
    override fun setup(
        target: Project,
        config: (LibraryExtension.() -> Unit)?
    ) {
        target.extensions.configure<LibraryExtension> {
            setupCommon(target)
            setupDefaultConfig()
            setupCompileOptions()
            setupBuildTypes()

            config?.invoke(this)
        }
    }

    override fun LibraryExtension.setupDefaultConfig() {
        defaultConfig {
            minSdk = AppConfig.MIN_SDK
        }
    }

    override fun LibraryExtension.setupBuildTypes() {
        buildTypes {
            release {
                isMinifyEnabled = false
            }
            sourceSets.findByName("androidDebug")?.apply {
                java.srcDir("src/androidDebug/java")
                res.srcDir("src/androidDebug/res")
                manifest.srcFile("src/androidDebug/AndroidManifest.xml")
            }
        }
    }
}