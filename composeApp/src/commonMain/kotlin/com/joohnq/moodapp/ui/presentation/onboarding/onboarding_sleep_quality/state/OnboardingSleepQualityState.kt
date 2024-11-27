package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_sleep_quality.state

import com.joohnq.moodapp.domain.SleepQuality
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_sleep_quality.event.OnboardingSleepQualityEvent
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingIntent

data class OnboardingSleepQualityState(
    val sliderValue: Float,
    val selectedSleepQuality: SleepQuality,
    val onEvent: (OnboardingSleepQualityEvent) -> Unit = {},
    val onAction: (OnboardingIntent) -> Unit = {}
)