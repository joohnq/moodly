package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_stress_level.state

import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_stress_level.event.OnboardingStressLevelEvent
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingIntent

data class OnboardingStressLevelState(
    val selectedOption: StressLevel,
    val onAction: (OnboardingIntent) -> Unit = {},
    val onEvent: (OnboardingStressLevelEvent) -> Unit = {},
)