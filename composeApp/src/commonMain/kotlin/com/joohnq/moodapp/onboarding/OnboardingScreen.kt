@file:OptIn(ExperimentalFoundationApi::class)

package com.joohnq.moodapp.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class OnboardingScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        SideEffect {
            navigator.push(OnboardingSleepQuality())
        }
        OnboardingMoodRateScreen()
    }
}
