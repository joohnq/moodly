import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.android.lint) apply false
}

subprojects {
    apply<SpotlessPlugin>()
    configure<SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("build/**/*.kt")
            ktlint(libs.versions.ktlint.get())
                .setEditorConfigPath("${rootDir}/spotless/.editorconfig")
                .editorConfigOverride(mapOf(
                    "max_line_length" to "120"
                ))
        }

        kotlinGradle {
            target("**/*.gradle.kts")
            ktlint(libs.versions.ktlint.get())
                .setEditorConfigPath("${rootDir}/spotless/.editorconfig")
        }

        format("misc") {
            target("**/*.md", "**/.gitignore", "**/*.properties")
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
}