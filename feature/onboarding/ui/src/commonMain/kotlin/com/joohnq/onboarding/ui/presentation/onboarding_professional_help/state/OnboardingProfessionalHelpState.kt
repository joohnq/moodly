package com.joohnq.onboarding.ui.presentation.onboarding_professional_help.state

import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent

data class OnboardingProfessionalHelpState(
    val selectedOption: ProfessionalHelp?,
    val onEvent: (OnboardingEvent) -> Unit = {},
    val onAction: (OnboardingViewModelIntent) -> Unit = {},
)