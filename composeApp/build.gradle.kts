import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.serialization)
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
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

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
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            implementation(projects.core.navigation)
            implementation(projects.core.di)
            implementation(projects.core.ui)
            implementation(projects.core.storage.data)
            implementation(projects.sharedResources)

            implementation(projects.feature.auth.ui)

            implementation(projects.feature.welcome.ui)
            implementation(projects.feature.freudScore.ui)
            implementation(projects.feature.onboarding.ui)

            implementation(projects.feature.home.ui)

            implementation(projects.feature.user.data)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.user.ui)

            implementation(projects.feature.mood.ui)
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.mood.data)

            implementation(projects.feature.healthJournal.ui)
            implementation(projects.feature.healthJournal.domain)
            implementation(projects.feature.healthJournal.data)

            implementation(projects.feature.sleepQuality.ui)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.feature.sleepQuality.data)

            implementation(projects.feature.stressLevel.ui)
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.feature.stressLevel.data)

            implementation(projects.feature.loading.ui)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.material)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.datetime)
            implementation(libs.navigation.compose)

            implementation(libs.bundles.koin)
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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }
    buildFeatures {
        compose = true
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