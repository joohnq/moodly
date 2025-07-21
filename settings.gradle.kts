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
    ":feature:sleep-quality:data",
    ":feature:sleep-quality:ui",
    ":feature:sleep-quality:domain",
    ":feature:stress-level:data",
    ":feature:stress-level:domain",
    ":feature:stress-level:ui",
    ":feature:user:data",
    ":feature:user:domain",
    ":feature:user:ui",
    ":feature:home:impl",
    ":feature:self-journal:data",
    ":feature:self-journal:domain",
    ":feature:self-journal:ui",
    ":feature:freud-score:api",
    ":feature:freud-score:impl",
    ":core:di",
    ":core:navigation",
    ":shared-resources",
    ":feature:splash:ui",
    ":feature:auth",
    ":feature:auth:ui",
    ":core:permission",
    ":core:storage:impl",
    ":core:storage:api",
    ":core:test",
    ":feature:security",
    ":feature:security:data",
    ":feature:security:domain",
    ":feature:security:ui",
    ":core:datastore",
    ":core:domain",
    ":feature:preferences:data",
    ":feature:preferences:domain",
    ":feature:preferences:ui",
    "backend"
)