package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis.event

sealed class OnboardingExpressionEvent {
    data object OnGoBack : OnboardingExpressionEvent()
    data object OnContinue : OnboardingExpressionEvent()
}