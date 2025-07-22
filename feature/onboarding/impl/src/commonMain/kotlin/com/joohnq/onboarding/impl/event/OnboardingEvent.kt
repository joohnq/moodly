package com.joohnq.onboarding.impl.event

sealed interface OnboardingEvent {
    data object OnGoBack : OnboardingEvent
    data object OnNavigateToNext : OnboardingEvent
}
