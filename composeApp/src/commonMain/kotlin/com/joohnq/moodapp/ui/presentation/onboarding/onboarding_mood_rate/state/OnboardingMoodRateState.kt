package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_mood_rate.state

import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.event.OnboardingProfessionalHelpEvent
import com.joohnq.moodapp.viewmodel.OnboardingIntent

data class OnboardingMoodRateState(
    val moodRatePadding: Int,
    val selectedMood: Mood,
    val onEvent: (OnboardingProfessionalHelpEvent) -> Unit = {},
    val onAction: (OnboardingIntent) -> Unit = {}
)