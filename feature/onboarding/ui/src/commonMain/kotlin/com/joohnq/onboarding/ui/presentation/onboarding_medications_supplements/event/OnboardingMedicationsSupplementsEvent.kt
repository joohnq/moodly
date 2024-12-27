package com.joohnq.onboarding.ui.presentation.onboarding_medications_supplements.event

sealed class OnboardingMedicationsSupplementsEvent {
    data object OnGoBack : OnboardingMedicationsSupplementsEvent()
    data object OnNavigateToOnboardingStressLevelScreen : OnboardingMedicationsSupplementsEvent()
}