package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.moodapp.onboarding.OnboardingScreen
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        MaterialTheme {
            Navigator(screen = OnboardingScreen())
        }
    }
}