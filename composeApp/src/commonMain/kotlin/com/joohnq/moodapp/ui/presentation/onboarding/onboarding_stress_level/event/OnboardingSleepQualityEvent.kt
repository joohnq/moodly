package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_stress_level.event

sealed class OnboardingStressLevelEvent {
    data object OnGoBack : OnboardingStressLevelEvent()
    data object OnNavigateToOnboardingExpressionAnalysisScreen : OnboardingStressLevelEvent()
}