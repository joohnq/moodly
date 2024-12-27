package com.joohnq.onboarding.ui.presentation.onboarding_sleep_quality.event

sealed class OnboardingSleepQualityEvent {
    data object OnGoBack : OnboardingSleepQualityEvent()
    data object OnNavigateToOnboardingMedicationSupplementsScreen : OnboardingSleepQualityEvent()
}