package com.joohnq.onboarding.ui.presentation.onboarding_sleep_quality.state

import com.joohnq.onboarding.ui.presentation.onboarding_sleep_quality.event.OnboardingSleepQualityEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.sleep_quality.domain.entity.SleepQuality

data class OnboardingSleepQualityState(
    val sliderValue: Float,
    val selectedSleepQuality: SleepQuality,
    val onEvent: (OnboardingSleepQualityEvent) -> Unit = {},
    val onAction: (OnboardingViewModelIntent) -> Unit = {}
)