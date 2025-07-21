package com.joohnq.onboarding.ui.event

sealed interface OnboardingEvent {
    data object OnGoBack : OnboardingEvent
    data object OnNavigateToNext : OnboardingEvent
}
