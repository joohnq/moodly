plugins {
    `kotlin-dsl`
    `version-catalog`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.library.gradle.plugin)
    implementation(libs.android.application.gradle.plugin)
    implementation(libs.kotlin.multiplatform.gradle.plugin)
    implementation(libs.compose.gradle.plugin)
    implementation(libs.detekt.gradle.plugin)
}

gradlePlugin {
    plugins {
        create("moodly-multiplatform-library") {
            id = "moodly.multiplatform.library"
            implementationClass = "buildLogic.plugins.KmpLibraryPlugin"
        }

        create("moodly-compose") {
            id = "moodly.compose"
            implementationClass = "buildLogic.plugins.ComposePlugin"
        }

        create("moodly-application") {
            id = "moodly.application"
            implementationClass = "buildLogic.plugins.AppPlugin"
        }

        create("moodly-android-library") {
            id = "moodly.android.library"
            implementationClass = "buildLogic.plugins.AndroidLibraryPlugin"
        }
    }
}