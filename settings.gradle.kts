@file:Suppress("UnstableApiUsage")

rootProject.name = "Moodly"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
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
    }
}

include(
    ":composeApp",
    ":core:database",
    ":core:ui",
    ":feature:welcome:ui",
    ":feature:mood:api",
    ":feature:mood:impl",
    ":feature:onboarding:impl",
    ":feature:sleep-quality:impl",
    ":feature:sleep-quality:api",
    ":feature:stress-level:data",
    ":feature:stress-level:domain",
    ":feature:stress-level:ui",
    ":feature:user:data",
    ":feature:user:domain",
    ":feature:user:ui",
    ":feature:home:impl",
    ":feature:self-journal:impl",
    ":feature:self-journal:api",
    ":feature:freud-score:api",
    ":feature:freud-score:impl",
    ":core:di",
    ":core:navigation",
    ":shared-resources",
    ":feature:splash:impl",
    ":feature:auth",
    ":feature:auth:ui",
    ":core:permission",
    ":core:storage:impl",
    ":core:storage:api",
    ":core:test",
    ":feature:security:impl",
    ":feature:security:api",
    ":core:datastore",
    ":core:domain",
    ":feature:preferences:impl",
    ":feature:preferences:api",
    "backend"
)