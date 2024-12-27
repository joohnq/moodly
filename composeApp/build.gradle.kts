@file:OptIn(ExperimentalComposeLibrary::class)

import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.mokkery)
    alias(libs.plugins.buildkonfig)
}

kotlin {
    targets.configureEach {
        compilations.configureEach {
            compileTaskProvider.get().compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            implementation(projects.shared.ui)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.bundles.viewmodel)
            implementation(libs.datetime)
            implementation(libs.serialization)
            implementation(libs.coroutines.core)
            implementation(libs.navigation.compose)
            implementation(libs.napier)

            // Koin
            api(libs.koin.core)
            implementation(libs.bundles.koin)

            // Room and SQLite
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            // Charts
            implementation(libs.charts)

            api(libs.generativeai)

            implementation(libs.bundles.voyager)
            implementation(libs.bundles.voyager.other)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(kotlin("test-annotations-common"))
            implementation(libs.truthish)
            implementation(compose.uiTest)
            implementation(libs.test.resources)
            implementation(libs.koin.core)
            implementation(libs.koin.test)
            implementation(libs.turbine)

            implementation(libs.voyager.navigator)
            implementation(libs.voyager.bottom.sheet.navigator)
            implementation(libs.voyager.tab.navigator)
            implementation(libs.voyager.transitions)
            implementation(libs.voyager.koin)

            implementation(libs.turbine)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)

            implementation(libs.ui.tooling.preview.desktop)
        }
    }
}

android {
    namespace = libs.versions.android.packageName.get()
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = libs.versions.android.packageName.get()
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = libs.versions.android.versionCode.get().toInt()
        versionName = libs.versions.android.versionName.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11

//        isCoreLibraryDesugaringEnabled = true
    }
    buildFeatures {
        compose = true
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(libs.androidx.benchmark.common)
    implementation(libs.androidx.ui.test.junit4.desktop)
    implementation(libs.androidx.core.i18n)
    implementation(libs.androidx.navigation.common.ktx)
    debugImplementation(compose.uiTooling)
    implementation(libs.coroutines.core)
    ksp(libs.room.compiler)
    add("kspAndroid", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
}

compose.desktop {
    application {
        mainClass = "${libs.versions.android.packageName.get()}.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = libs.versions.android.packageName.get()
            packageVersion = libs.versions.android.versionName.get()
        }
    }
}

buildkonfig {
    packageName = libs.versions.android.packageName.get()
    defaultConfigs {
        val localPropsFile = rootProject.file("local.properties")
        val localProperties = Properties()
        if (localPropsFile.exists()) {
            runCatching {
                localProperties.load(localPropsFile.inputStream())
            }.getOrElse {
                it.printStackTrace()
            }
        }
        buildConfigField(
            FieldSpec.Type.STRING,
            "GEMINI_API_KEY",
            localProperties["gemini_api_key"]?.toString() ?: ""
        )
    }
}

// Prevent the bug with room when generate an APK
tasks.withType<Test> {
    if (name == "mergeDebugAndroidTestAssets") {
        enabled = false
    }
}

tasks.withType<Test> {
    if (name == "copyRoomSchemasToAndroidTestAssetsDebugAndroidTest") {
        enabled = false
    }
}

tasks.whenTaskAdded {
    if (name.contains("copyRoomSchemasToAndroidTestAssetsDebugAndroidTest")) {
        enabled = false
    }
}

gradle.taskGraph.whenReady {
    allTasks.onEach { task ->
        if (task.name.contains("androidTest") || task.name.contains("connectedAndroidTest")) {
            task.enabled = false
        }
    }
}
