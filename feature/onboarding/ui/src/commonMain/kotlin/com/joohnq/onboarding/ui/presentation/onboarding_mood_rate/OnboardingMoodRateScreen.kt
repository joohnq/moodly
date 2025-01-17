package com.joohnq.onboarding.ui.presentation.onboarding_mood_rate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.CustomScreen
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.presentation.onboarding_mood_rate.state.OnboardingMoodRateState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel

class OnboardingMoodRateScreen(
    private val onNavigateToProfessionalHelp: () -> Unit,
    private val onGoBack: () -> Unit,
) : CustomScreen<OnboardingMoodRateState>() {
    @Composable
    override fun Screen(): OnboardingMoodRateState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.state.collectAsState()

        fun onEvent(event: OnboardingEvent) =
            when (event) {
                OnboardingEvent.OnNavigateToNext -> onNavigateToProfessionalHelp()
                OnboardingEvent.OnGoBack -> onGoBack()
            }

        return OnboardingMoodRateState(
            selectedMood = onboardingState.statsRecord.mood,
            onAction = onboardingViewModel::onAction,
            onEvent = ::onEvent,
        )
    }

    @Composable
    override fun UI(state: OnboardingMoodRateState) = OnboardingMoodRateUI(state)

    object OnboardingMoodRateTestTag {
        const val ROULETTE = "ROULETTE"
    }
}