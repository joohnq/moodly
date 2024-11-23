package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.event

sealed class OnboardingProfessionalHelpEvent {
    data object OnGoBack : OnboardingProfessionalHelpEvent()
    data object OnNavigateToOnboardingPhysicalSymptomsScreen : OnboardingProfessionalHelpEvent()
}