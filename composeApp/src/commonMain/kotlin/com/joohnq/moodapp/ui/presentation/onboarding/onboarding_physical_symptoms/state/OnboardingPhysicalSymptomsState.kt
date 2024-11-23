package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_physical_symptoms.state

import com.joohnq.moodapp.domain.PhysicalSymptoms
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.event.OnboardingProfessionalHelpEvent
import com.joohnq.moodapp.viewmodel.OnboardingIntent

data class OnboardingPhysicalSymptomsState(
    val selectedOption: PhysicalSymptoms?,
    val onEvent: (OnboardingProfessionalHelpEvent) -> Unit = {},
    val onAction: (OnboardingIntent) -> Unit = {},
)
