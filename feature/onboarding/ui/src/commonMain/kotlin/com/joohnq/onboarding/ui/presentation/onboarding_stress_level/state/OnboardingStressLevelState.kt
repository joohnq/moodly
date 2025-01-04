package com.joohnq.onboarding.ui.presentation.onboarding_stress_level.state

import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.stress_level.ui.StressLevelResource

data class OnboardingStressLevelState(
    val selectedOption: StressLevelResource,
    val onEvent: (OnboardingEvent) -> Unit = {},
    val onAction: (OnboardingViewModelIntent) -> Unit = {},
)