package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.state

import com.joohnq.moodapp.domain.ProfessionalHelp
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.event.OnboardingProfessionalHelpEvent
import com.joohnq.moodapp.viewmodel.OnboardingIntent

data class OnboardingProfessionalHelpState(
    val selectedOption: ProfessionalHelp?,
    val onEvent: (OnboardingProfessionalHelpEvent) -> Unit = {},
    val onAction: (OnboardingIntent) -> Unit = {},
)