package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.moodapp.welcome.WelcomeScreen
import org.koin.compose.KoinApplication
import org.koin.dsl.module

@Composable
fun App() {
    KoinApplication(application = {
        modules(module{})
    }) {
        MaterialTheme {
            Navigator(screen = WelcomeScreen())
        }
    }
}