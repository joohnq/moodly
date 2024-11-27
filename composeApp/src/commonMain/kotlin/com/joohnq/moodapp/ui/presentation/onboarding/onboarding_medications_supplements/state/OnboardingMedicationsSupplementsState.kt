package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_medications_supplements.state

import com.joohnq.moodapp.domain.MedicationsSupplements
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_medications_supplements.event.OnboardingMedicationsSupplementsEvent
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingIntent

data class OnboardingMedicationSupplementsState(
    val selectedOption: MedicationsSupplements?,
    val onEvent: (OnboardingMedicationsSupplementsEvent) -> Unit = {},
    val onAction: (OnboardingIntent) -> Unit = {}
)