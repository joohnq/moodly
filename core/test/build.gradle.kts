import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "test"
            isStatic = true
        }
    }

    sourceSets {
        androidUnitTest.dependencies {
            implementation(libs.androidx.startup.runtime)
            implementation(libs.android.driver)

            implementation(libs.androidx.core.ktx)
            implementation(libs.robolectric)
            implementation(libs.junit)
        }
        commonMain.dependencies {
            implementation(libs.datetime)
        }
        commonTest.dependencies {
            implementation(projects.core.database)
            implementation(libs.bundles.test)
            implementation(libs.coroutines.extensions)
        }
        nativeTest.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
    }
}

android {
    namespace = "com.joohnq.core.test"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
dependencies {
    implementation(libs.androidx.core.ktx)
}
