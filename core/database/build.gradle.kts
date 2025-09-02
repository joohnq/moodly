
plugins {
    id("moodly.android.library")
    id("moodly.multiplatform.library")

    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.sqldelight.android.driver)
        }
        commonMain.dependencies {
            implementation(projects.feature.gratefulness.api)
            implementation(libs.bundles.koin)

            implementation(libs.kotlin.datetime)

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.primitive.adapters)
        }
        nativeMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
        jvmMain.dependencies {
            implementation(libs.sqldelight.jvm.driver)
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

sqldelight {
    databases {
        create("AppDatabaseSql") {
            packageName.set("com.joohnq.database")
        }
    }
}
