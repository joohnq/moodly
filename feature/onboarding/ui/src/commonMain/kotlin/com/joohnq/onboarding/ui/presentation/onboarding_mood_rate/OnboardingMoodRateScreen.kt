package com.joohnq.onboarding.ui.presentation.onboarding_mood_rate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel

@Composable
fun OnboardingMoodRateScreen(
    onNavigateToProfessionalHelp: () -> Unit,
    onGoBack: () -> Unit,
) {
    val onboardingViewModel: OnboardingViewModel = sharedViewModel()
    val state by onboardingViewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.OnNavigateToNext -> onNavigateToProfessionalHelp()
            OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingMoodRateUI(
        record = state.moodRecord,
        onAction = onboardingViewModel::onAction,
        onEvent = ::onEvent,
    )
}