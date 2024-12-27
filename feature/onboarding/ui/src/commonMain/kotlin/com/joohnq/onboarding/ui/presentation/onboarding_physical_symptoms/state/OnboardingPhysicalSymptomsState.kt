package com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms.state

import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms.event.OnboardingPhysicalSymptomsEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent

data class OnboardingPhysicalSymptomsState(
    val selectedOption: PhysicalSymptoms?,
    val onEvent: (OnboardingPhysicalSymptomsEvent) -> Unit = {},
    val onAction: (OnboardingViewModelIntent) -> Unit = {},
)
