plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

kotlin {
    targets.configureEach {
        compilations.configureEach {
            compileTaskProvider.get().compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }

    androidLibrary {
        namespace = "com.joohnq.auth.domain"

        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "auth.domain"
            isStatic = true
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.feature.user.domain)
                implementation(projects.core.domain)

                implementation(libs.kotlin.stdlib)
                implementation(libs.bundles.koin)
            }
        }
    }

}