package com.joohnq.onboarding.impl.ui.event

sealed interface OnboardingEvent {
    data object OnGoBack : OnboardingEvent

    data object NavigateNext : OnboardingEvent
}
