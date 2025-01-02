package com.joohnq.onboarding.ui.presentation.onboarding_mood_rate.state

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent

data class OnboardingMoodRateState(
    val selectedMood: Mood,
    val onEvent: (OnboardingEvent) -> Unit,
    val onAction: (OnboardingViewModelIntent) -> Unit,
)