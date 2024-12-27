package com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis.event

sealed class OnboardingExpressionEvent {
    data object OnGoBack : OnboardingExpressionEvent()
    data object OnContinue : OnboardingExpressionEvent()
}