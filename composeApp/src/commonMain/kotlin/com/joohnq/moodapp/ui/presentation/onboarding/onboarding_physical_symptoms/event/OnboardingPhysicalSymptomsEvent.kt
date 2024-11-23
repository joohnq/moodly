package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_physical_symptoms.event

sealed class OnboardingPhysicalSymptomsEvent {
    data object OnGoBack : OnboardingPhysicalSymptomsEvent()
    data object OnContinue : OnboardingPhysicalSymptomsEvent()
}