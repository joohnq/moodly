package com.joohnq.moodapp

import androidx.compose.ui.window.ComposeUIViewController
import com.joohnq.moodapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}