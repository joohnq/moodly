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
include(":core:database")
include(":core:ui")
include(":shared:domain")
include(":shared:ui")
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
