package com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms.event

sealed class OnboardingPhysicalSymptomsEvent {
    data object OnGoBack : OnboardingPhysicalSymptomsEvent()
    data object OnNavigateOnboardingSleepQualityScreen : OnboardingPhysicalSymptomsEvent()
}