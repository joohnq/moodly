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
        namespace = "com.joohnq.auth.data"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "auth.data"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation("com.google.firebase:firebase-auth-ktx:23.2.0")
        }

        commonMain {
            dependencies {
                api(libs.gitlive.firebase.auth)

                implementation(projects.feature.auth.domain)

                implementation(libs.kotlin.stdlib)
                implementation(libs.coroutines.core)
                implementation(libs.coroutines.extensions)

                implementation(libs.bundles.koin)

                implementation(libs.bundles.base)
            }
        }
    }
}