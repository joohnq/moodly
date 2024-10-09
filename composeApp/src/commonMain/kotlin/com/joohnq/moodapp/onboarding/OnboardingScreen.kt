package com.joohnq.moodapp.onboarding

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class OnboardingScreen : Screen {
    @Composable
    override fun Content() {
        OnboardingMoodRateScreen()
    }
}
