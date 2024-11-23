package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_medications_supplements.event

sealed class OnboardingMedicationsSupplementsEvent {
    data object OnGoBack : OnboardingMedicationsSupplementsEvent()
    data object OnNavigateToOnboardingStressLevelScreen : OnboardingMedicationsSupplementsEvent()
}