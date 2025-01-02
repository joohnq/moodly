package com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms.state

import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent

data class OnboardingPhysicalSymptomsState(
    val selectedOption: PhysicalSymptoms?,
    val onEvent: (OnboardingEvent) -> Unit = {},
    val onAction: (OnboardingViewModelIntent) -> Unit = {},
)
