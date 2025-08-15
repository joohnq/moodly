package buildLogic.configs

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object AppConfig {
    const val COMPILE_SDK = 35
    const val MIN_SDK = 26
    const val TARGET_SDK = 35
    const val VERSION_CODE = 54
    const val VERSION_NAME = "1.2.0"
    const val APPLICATION_NAME = "com.joohnq."
    const val APPLICATION_ID = "${APPLICATION_NAME}moodapp"

    val javaVersion = JavaVersion.VERSION_11
    val jvmTargetVersion = JvmTarget.JVM_11
}