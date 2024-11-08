package com.joohnq.moodapp

import androidx.compose.ui.window.ComposeUIViewController
import com.joohnq.moodapp.di.KoinInitializer

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    App()
}