package com.joohnq.onboarding.impl.ui.presentation.onboarding_mood_rate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingMoodRateScreen(
    onNavigateToProfessionalHelp: () -> Unit,
    onGoBack: () -> Unit,
) {
    val onboardingViewModel: OnboardingViewModel =
        sharedViewModel()
    val state by onboardingViewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.OnNavigateToNext -> onNavigateToProfessionalHelp()
            OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingMoodRateContent(
        state = state.moodRecord,
        onAction = onboardingViewModel::onIntent,
        onEvent = ::onEvent
    )
}