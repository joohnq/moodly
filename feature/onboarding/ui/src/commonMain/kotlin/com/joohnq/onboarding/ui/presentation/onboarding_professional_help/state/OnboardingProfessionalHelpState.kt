package com.joohnq.onboarding.ui.presentation.onboarding_professional_help.state

import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.onboarding.ui.presentation.onboarding_professional_help.event.OnboardingProfessionalHelpEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent

data class OnboardingProfessionalHelpState(
    val selectedOption: ProfessionalHelp?,
    val onEvent: (OnboardingProfessionalHelpEvent) -> Unit = {},
    val onAction: (OnboardingViewModelIntent) -> Unit = {},
)