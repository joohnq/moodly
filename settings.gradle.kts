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
    "backend",
    ":composeApp",
    ":core:database",
    ":core:datastore",
    ":core:domain",
    ":core:navigation",
    ":core:permission",
    ":core:storage:api",
    ":core:storage:impl",
    ":core:test",
    ":core:ui",
    ":feature:auth:impl",
    ":feature:freud-score:api",
    ":feature:freud-score:impl",
    ":feature:home:impl",
    ":feature:mood:api",
    ":feature:mood:impl",
    ":feature:onboarding:impl",
    ":feature:preferences:api",
    ":feature:preferences:impl",
    ":feature:security:api",
    ":feature:security:impl",
    ":feature:self-journal:api",
    ":feature:self-journal:impl",
    ":feature:sleep-quality:api",
    ":feature:sleep-quality:impl",
    ":feature:splash:impl",
    ":feature:stress-level:api",
    ":feature:stress-level:impl",
    ":feature:user:api",
    ":feature:user:impl",
    ":feature:welcome:impl",
    ":shared-resources",
)