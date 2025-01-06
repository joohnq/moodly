package com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms.state

import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.user.ui.resource.PhysicalSymptomsResource

data class OnboardingPhysicalSymptomsState(
    val selectedOption: PhysicalSymptomsResource?,
    val onEvent: (OnboardingEvent) -> Unit = {},
    val onAction: (OnboardingViewModelIntent) -> Unit = {},
)
