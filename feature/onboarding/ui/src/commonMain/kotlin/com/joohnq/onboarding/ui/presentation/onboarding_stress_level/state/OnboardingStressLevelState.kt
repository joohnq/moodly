package com.joohnq.onboarding.ui.presentation.onboarding_stress_level.state

import com.joohnq.onboarding.ui.presentation.onboarding_stress_level.event.OnboardingStressLevelEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.stress_level.domain.entity.StressLevel

data class OnboardingStressLevelState(
    val selectedOption: StressLevel,
    val onAction: (OnboardingViewModelIntent) -> Unit = {},
    val onEvent: (OnboardingStressLevelEvent) -> Unit = {},
)