plugins {
    id("moodly.multiplatform.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)
        }
    }
}