package com.joohnq.onboarding.ui.event

sealed class OnboardingEvent {
    data object OnGoBack : OnboardingEvent()
    data object OnNavigateToNext : OnboardingEvent()
}
