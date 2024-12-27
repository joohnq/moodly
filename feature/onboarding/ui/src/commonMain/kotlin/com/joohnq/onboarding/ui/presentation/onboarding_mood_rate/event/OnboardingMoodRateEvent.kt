package com.joohnq.onboarding.ui.presentation.onboarding_mood_rate.event

sealed class OnboardingMoodRateEvent {
    data object OnGoBack : OnboardingMoodRateEvent()
    data object OnNavigateToOnboardingProfessionalHelpScreen : OnboardingMoodRateEvent()
}