package com.joohnq.onboarding.ui.presentation.onboarding_mood_rate.state

import com.joohnq.domain.entity.Mood
import com.joohnq.onboarding.ui.presentation.onboarding_mood_rate.event.OnboardingMoodRateEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent

data class OnboardingMoodRateState(
    val selectedMood: Mood,
    val onEvent: (OnboardingMoodRateEvent) -> Unit,
    val onAction: (OnboardingViewModelIntent) -> Unit
)