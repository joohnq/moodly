package com.joohnq.moodapp.view.screens.onboarding

sealed class OnboardingAction {
    data object OnGoBack : OnboardingAction()
    data object OnContinue : OnboardingAction()
}