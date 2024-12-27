package com.joohnq.onboarding.ui.presentation.onboarding_stress_level.event

sealed class OnboardingStressLevelEvent {
    data object OnGoBack : OnboardingStressLevelEvent()
    data object OnNavigateToOnboardingExpressionAnalysisScreen : OnboardingStressLevelEvent()
}