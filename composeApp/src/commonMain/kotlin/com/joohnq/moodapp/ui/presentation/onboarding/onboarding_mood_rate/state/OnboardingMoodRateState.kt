package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_mood_rate.state

import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingIntent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_mood_rate.event.OnboardingMoodRateEvent

data class OnboardingMoodRateState(
    val selectedMood: Mood,
    val onEvent: (OnboardingMoodRateEvent) -> Unit,
    val onAction: (OnboardingIntent) -> Unit
)