import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
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
            jvmTarget.set(JvmTarget.JVM_17)
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

            implementation(
                project.dependencies.platform(
                    libs.firebase.android.bom
                )
            )
        }
        commonMain.dependencies {
            //Firebase
            api(libs.gitlive.firebase.crashlytics)
            api(libs.gitlive.firebase.firestore)
            api(libs.gitlive.firebase.analytics)

            //Datastore
            implementation(projects.core.datastore)

            //Core Navigation
            implementation(projects.core.navigation)

            //Core UI
            implementation(projects.core.ui)

            //Core Domain
            implementation(projects.core.domain)

            //Storage
            implementation(projects.core.storage.data)

            //Shared Resources
            implementation(projects.sharedResources)

            //Security
            implementation(projects.feature.security.data)
            implementation(projects.feature.security.ui)
            implementation(projects.feature.security.domain)

            //Auth
            implementation(projects.feature.auth.data)
            implementation(projects.feature.auth.domain)
            implementation(projects.feature.auth.ui)

            //Preferences
            implementation(projects.feature.preferences.domain)
            implementation(projects.feature.preferences.data)
            implementation(projects.feature.preferences.ui)

            //Welcome
            implementation(projects.feature.welcome.ui)

            //Freud Score
            implementation(projects.feature.freudScore.ui)

            //Onboarding
            implementation(projects.feature.onboarding.ui)

            //Home
            implementation(projects.feature.home.ui)

            //User
            implementation(projects.feature.user.data)
            implementation(projects.feature.user.domain)
            implementation(projects.feature.user.ui)

            //Mood
            implementation(projects.feature.mood.ui)
            implementation(projects.feature.mood.domain)
            implementation(projects.feature.mood.data)

            //Self Journal
            implementation(projects.feature.selfJournal.ui)
            implementation(projects.feature.selfJournal.domain)
            implementation(projects.feature.selfJournal.data)

            //Sleep Quality
            implementation(projects.feature.sleepQuality.ui)
            implementation(projects.feature.sleepQuality.domain)
            implementation(projects.feature.sleepQuality.data)

            //Stress Level
            implementation(projects.feature.stressLevel.ui)
            implementation(projects.feature.stressLevel.domain)
            implementation(projects.feature.stressLevel.data)

            //Splash Screen
            implementation(projects.feature.splash.ui)

            //Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.material)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.kotlin.datetime)
            implementation(libs.navigation.compose)

            implementation(libs.bundles.koin)

            implementation(libs.bundles.kmpauth)
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
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            ndk.debugSymbolLevel = "FULL"
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    base {
        archivesName = "Moodly-v${defaultConfig.versionCode}"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    ndkVersion = "27.0.12077973"
}
