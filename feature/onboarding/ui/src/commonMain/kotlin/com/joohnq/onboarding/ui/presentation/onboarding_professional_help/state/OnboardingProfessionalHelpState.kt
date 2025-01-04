package com.joohnq.onboarding.ui.presentation.onboarding_professional_help.state

import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.user.ui.ProfessionalHelpResource

data class OnboardingProfessionalHelpState(
    val selectedOption: ProfessionalHelpResource?,
    val onEvent: (OnboardingEvent) -> Unit = {},
    val onAction: (OnboardingViewModelIntent) -> Unit = {},
)