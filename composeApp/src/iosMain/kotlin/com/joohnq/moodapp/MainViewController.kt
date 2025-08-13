package com.joohnq.moodapp

import androidx.compose.ui.window.ComposeUIViewController
import com.joohnq.moodapp.di.KoinInitializer
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.crashlytics.crashlytics
import dev.gitlive.firebase.initialize

@Suppress("ktlint:standard:function-naming", "FunctionNaming")
fun MainViewController() =
    ComposeUIViewController(
        configure = {
            KoinInitializer().init()
        }
    ) {
        App()
    }

fun initialize() {
    Firebase.initialize()
    Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)
}
