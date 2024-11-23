package com.joohnq.moodapp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.joohnq.moodapp.ui.navigation.RootComponent

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinProject",
    ) {
        val root =
            remember {
                RootComponent(
                    componentContext = DefaultComponentContext(lifecycle = LifecycleRegistry()),
                )
            }
        App(root)
    }
}