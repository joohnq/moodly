package buildLogic.configs

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal object AppConfig {
    const val COMPILE_SDK = 35
    const val MIN_SDK = 26
    const val TARGET_SDK = 35
    const val VERSION_CODE = 5
    const val VERSION_NAME = "1.0.1"
    const val APPLICATION_ID = "com.joohnq.moodapp"

    val javaVersion = JavaVersion.VERSION_11
    val jvmTargetVersion = JvmTarget.JVM_11
    const val JVM_TARGET = "11"
}