package buildLogic.configs

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal object AppConfig {
    const val compileSdk = 35
    const val minSdk = 26
    const val targetSdk = 35
    const val versionCode = 5
    const val versionName = "1.0.1"
    const val appId = "com.joohnq.moodapp"

    val javaVersion = JavaVersion.VERSION_11
    val jvmTargetMp = JvmTarget.JVM_11
    const val jvmTarget = "11"
}