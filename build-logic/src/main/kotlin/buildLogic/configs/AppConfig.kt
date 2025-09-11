package buildLogic.configs

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object AppConfig {
    const val COMPILE_SDK = 36
    const val MIN_SDK = 26
    const val TARGET_SDK = 36
    const val VERSION_CODE = 60
    const val VERSION_NAME = "1.3.0"
    const val APPLICATION_NAME = "com.joohnq"
    const val APPLICATION_ID = "${APPLICATION_NAME}.moodapp"

    val javaVersion = JavaVersion.VERSION_11
    val jvmTargetVersion = JvmTarget.JVM_11
}