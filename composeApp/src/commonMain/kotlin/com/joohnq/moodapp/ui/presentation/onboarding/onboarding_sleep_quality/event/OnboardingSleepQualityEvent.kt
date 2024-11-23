package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_sleep_quality.event

sealed class OnboardingSleepQualityEvent {
    data object OnGoBack : OnboardingSleepQualityEvent()
    data object OnNavigateToOnboardingMedicationSupplementsScreen : OnboardingSleepQualityEvent()
}