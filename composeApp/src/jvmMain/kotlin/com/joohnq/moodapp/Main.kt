package com.joohnq.moodapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.joohnq.moodapp.di.KoinInitializer

fun main() {
    KoinInitializer().init()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            alwaysOnTop = true,
            title = "Moodly"
        ) {
            App()
        }
    }
}
