@file:Suppress("UnstableApiUsage")

rootProject.name = "Moodly"
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
    ":feature:mood:ui",
    ":feature:mood:domain",
    ":feature:mood:data",
    ":feature:onboarding:ui",
    ":feature:sleep-quality:data",
    ":feature:sleep-quality:ui",
    ":feature:sleep-quality:domain",
    ":feature:stress-level:data",
    ":feature:stress-level:domain",
    ":feature:stress-level:ui",
    ":feature:user:data",
    ":feature:user:domain",
    ":feature:user:ui",
    ":feature:home:ui",
    ":feature:self-journal:data",
    ":feature:self-journal:domain",
    ":feature:self-journal:ui",
    ":feature:freud-score:domain",
    ":feature:freud-score:ui",
    ":core:di",
    ":core:navigation",
    ":shared-resources",
    ":feature:splash:ui",
    ":feature:auth",
    ":feature:auth:ui",
    ":core:permission",
    ":core:storage:data",
    ":core:storage:domain",
    ":core:test",
    ":feature:security",
    ":feature:security:data",
    ":feature:security:domain",
    ":feature:security:ui",
    ":core:datastore",
    ":core:domain",
    ":core:cryptography:data",
    ":core:cryptography:domain",
    ":feature:preferences:data",
    ":feature:preferences:domain",
    ":feature:preferences:ui",
    "backend"
)