rootProject.name = "MoodApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://androidx.dev/storage/compose-compiler/repository")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://androidx.dev/storage/compose-compiler/repository")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }
}

include(":composeApp")
include(":core:database")
include(":core:ui")
include(":feature:welcome:ui")
include(":feature:mood:ui")
include(":feature:mood:domain")
include(":feature:mood:data")
include(":feature:onboarding:ui")
include(":feature:sleep-quality:data")
include(":feature:sleep-quality:ui")
include(":feature:sleep-quality:domain")
include(":feature:stress-level:data")
include(":feature:stress-level:domain")
include(":feature:stress-level:ui")
include(":feature:user:data")
include(":feature:user:domain")
include(":feature:user:ui")
include(":feature:home:ui")
include(":feature:health-journal:data")
include(":feature:health-journal:domain")
include(":feature:health-journal:ui")
include(":feature:freud-score:domain")
include(":feature:freud-score:ui")
include(":core:di")
include(":core:navigation")
include(":shared-resources")
include(":feature:loading:ui")
include(":feature:profile")
include(":feature:auth")
include(":feature:auth:ui")
include(":core:permission")
include(":core:storage:data")
include(":core:storage:domain")
include(":core:test")
