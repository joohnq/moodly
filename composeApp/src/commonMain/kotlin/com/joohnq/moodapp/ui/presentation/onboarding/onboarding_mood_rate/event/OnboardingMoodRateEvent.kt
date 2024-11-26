package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_mood_rate.event

sealed class OnboardingMoodRateEvent {
    data object OnGoBack : OnboardingMoodRateEvent()
    data object OnNavigateToOnboardingProfessionalHelpScreen : OnboardingMoodRateEvent()
}