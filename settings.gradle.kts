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
include(":feature:mood")
include(":feature:welcome:ui")
include(":shared:domain")
include(":shared:ui")
include(":feature:home")
include(":feature:user")
include(":feature:mood:ui")
include(":feature:mood:domain")
include(":feature:onboarding:domain")
include(":feature:onboarding:ui")
include(":feature:sleep-quality:data")
include(":feature:sleep-quality:ui")
include(":feature:sleep-quality:domain")
include(":feature:mood:data")
include(":feature:stress-level:data")
include(":feature:stress-level:domain")
include(":feature:stress-level:ui")
include(":feature:user:data")
include(":feature:user:domain")
include(":feature:user:ui")
include(":feature:home:domain")
include(":feature:home:ui")
include(":feature:health-journal:data")
include(":feature:health-journal:domain")
include(":feature:health-journal:ui")
include(":feature:freud-score:domain")
include(":feature:freud-score:ui")
include(":core")
include(":shared:database")
