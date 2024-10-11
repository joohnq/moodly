@file:OptIn(ExperimentalFoundationApi::class)

package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class OnboardingScreen : Screen {
    @Composable
    override fun Content() {
        OnboardingMoodRateScreen()
    }
}
