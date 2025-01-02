package com.joohnq.onboarding.ui.presentation.onboarding_mood_rate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.ui.presentation.onboarding_mood_rate.event.OnboardingMoodRateEvent
import com.joohnq.onboarding.ui.presentation.onboarding_mood_rate.state.OnboardingMoodRateState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel

class OnboardingMoodRateScreen : CustomScreen<OnboardingMoodRateState>() {
    @Composable
    override fun Screen(): OnboardingMoodRateState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.state.collectAsState()

        fun onEvent(event: OnboardingMoodRateEvent) =
            when (event) {
                OnboardingMoodRateEvent.OnNavigateToOnboardingProfessionalHelpScreen -> {}
//                    onNavigate(OnboardingProfessionalHelpScreen())

                OnboardingMoodRateEvent.OnGoBack -> {}
//                    onGoBack()
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