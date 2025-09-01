package buildLogic.plugins

import buildLogic.configs.AppConfig
import buildLogic.extensions.getPlugin
import buildLogic.plugins.android.AppAndroidSettings
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Zip
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

class AppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            installPlugins()
            androidSettings()
            desktopSettings()
            setupTasks()
        }
    }

    private fun Project.installPlugins() {
        pluginManager.apply(
            getPlugin("android-application").pluginId
        )
        pluginManager.apply(
            getPlugin("jetbrains-compose").pluginId
        )
    }

    private fun Project.desktopSettings() {
        configure<ComposeExtension> {
            extensions.configure<DesktopExtension> {
                application {
                    mainClass = "com.joohnq.joohnq.MainKt"

                    nativeDistributions {
                        targetFormats(
                            TargetFormat.Dmg,
                            TargetFormat.Msi,
                            TargetFormat.Deb
                        )
                        packageName = "com.joohnq.moodapp"
                        packageVersion = "1.0.0"
                    }
                }
            }
        }
    }

    private fun Project.androidSettings() {
        AppAndroidSettings().setup(this) {
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            ndkVersion = "27.0.12077973"
        }
    }

    private fun Project.setupTasks() {
        tasks.register("printVersionCode") {
            doLast {
                println(AppConfig.VERSION_CODE)
            }
        }

        tasks.register("zipNativeDebugSymbols", Zip::class.java) {
            from("${layout.buildDirectory.get().asFile}/intermediates/merged_native_libs/release/out/lib")
            archiveFileName.set("native-debug-symbols.zip")
            destinationDirectory.set(layout.buildDirectory.dir("outputs"))
        }
    }
}