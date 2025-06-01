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

include(":composeApp")
include(":core:database")
include(":core:ui")
include(":core:di")
include(":core:test")
include(":core:domain")
include(":core:datastore")
include(":core:navigation")
include(":core:permission")
include(":core:storage:data")
include(":core:storage:domain")
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
include(":feature:self-journal:data")
include(":feature:self-journal:domain")
include(":feature:self-journal:ui")
include(":feature:freud-score:domain")
include(":feature:freud-score:ui")
include(":feature:splash:ui")
include(":feature:security")
include(":feature:security:data")
include(":feature:security:domain")
include(":feature:security:ui")
include(":feature:preferences:data")
include(":feature:preferences:domain")
include(":feature:preferences:ui")
include(":feature:auth:ui")
include(":feature:auth:domain")
include(":feature:auth:data")
include(":shared-resources")
