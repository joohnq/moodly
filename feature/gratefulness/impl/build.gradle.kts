plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")
    id("moodly.compose")

    alias(libs.plugins.serialization)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.database)

            implementation(projects.core.domain)

            implementation(projects.core.ui)

            implementation(projects.feature.gratefulness.api)

            implementation(projects.sharedResources)

            implementation(libs.coroutines.extensions)
            implementation(libs.serialization)
            implementation(libs.kotlin.datetime)

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            implementation(libs.bundles.koin)
            implementation(libs.bundles.viewmodel)
        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    kspAndroid(libs.room.compiler)
    kspIosSimulatorArm64(libs.room.compiler)
    kspIosX64(libs.room.compiler)
    kspIosArm64(libs.room.compiler)
    kspJvm(libs.room.compiler)
}
