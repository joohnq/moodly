package com.joohnq.onboarding.ui.presentation.onboarding_mood_rate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.ui.viewmodel.OnboardingContract
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingMoodRateScreen(
    onNavigateToNext: () -> Unit,
    onGoBack: () -> Unit,
) {
    val onboardingViewModel: OnboardingViewModel = sharedViewModel()
    val state by onboardingViewModel.state.collectAsState()

    fun onEvent(event: OnboardingContract.Event) =
        when (event) {
            OnboardingContract.Event.OnContinue -> onNavigateToNext()
            OnboardingContract.Event.GoBack -> onGoBack()
        }

    OnboardingMoodRateUI(
        record = state.moodRecord,
        onIntent = onboardingViewModel::onIntent,
        onEvent = ::onEvent,
    )
}