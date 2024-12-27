package com.joohnq.mood

import androidx.compose.ui.window.ComposeUIViewController
import com.joohnq.mood.di.KoinInitializer

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    App()
}