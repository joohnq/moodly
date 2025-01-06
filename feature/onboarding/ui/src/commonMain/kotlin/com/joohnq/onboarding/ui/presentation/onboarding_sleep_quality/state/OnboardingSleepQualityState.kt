package com.joohnq.onboarding.ui.presentation.onboarding_sleep_quality.state

import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource

data class OnboardingSleepQualityState(
    val sliderValue: Float,
    val selectedSleepQuality: SleepQualityResource,
    val onEvent: (OnboardingEvent) -> Unit = {},
    val onAction: (OnboardingViewModelIntent) -> Unit = {},
)