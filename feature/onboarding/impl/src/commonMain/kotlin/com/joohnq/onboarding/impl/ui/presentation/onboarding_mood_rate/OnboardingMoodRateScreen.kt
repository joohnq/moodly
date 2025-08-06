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
    viewModel: OnboardingViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            OnboardingEvent.NavigateNext -> onNavigateToProfessionalHelp()
            OnboardingEvent.OnGoBack -> onGoBack()
        }
    }

    OnboardingMoodRateContent(
        state = state.moodRecord,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}
