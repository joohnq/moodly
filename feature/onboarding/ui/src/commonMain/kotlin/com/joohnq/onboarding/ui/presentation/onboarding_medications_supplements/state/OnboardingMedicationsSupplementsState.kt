package com.joohnq.onboarding.ui.presentation.onboarding_medications_supplements.state

import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.user.ui.resource.MedicationsSupplementsResource

data class OnboardingMedicationsSupplementsState(
    val selectedOption: MedicationsSupplementsResource?,
    val onEvent: (OnboardingEvent) -> Unit = {},
    val onAction: (OnboardingViewModelIntent) -> Unit = {},
)