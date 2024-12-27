package com.joohnq.onboarding.ui.presentation.onboarding_professional_help.event

sealed class OnboardingProfessionalHelpEvent {
    data object OnGoBack : OnboardingProfessionalHelpEvent()
    data object OnNavigateToOnboardingPhysicalSymptomsScreen : OnboardingProfessionalHelpEvent()
}