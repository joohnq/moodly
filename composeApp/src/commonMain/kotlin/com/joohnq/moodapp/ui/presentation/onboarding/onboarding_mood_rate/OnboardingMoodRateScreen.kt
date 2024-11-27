package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_mood_rate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.ScreenDimensions
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_mood_rate.event.OnboardingMoodRateEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_mood_rate.state.OnboardingMoodRateState
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.OnboardingProfessionalHelpScreen
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingViewModel
import org.koin.compose.koinInject

class OnboardingMoodRateScreen : CustomScreen<OnboardingMoodRateState>() {
    @Composable
    override fun Screen(): OnboardingMoodRateState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val screenDimensions: ScreenDimensions = koinInject()
        val onboardingState by onboardingViewModel.onboardingState.collectAsState()

        fun onEvent(event: OnboardingMoodRateEvent) =
            when (event) {
                OnboardingMoodRateEvent.OnNavigateToOnboardingProfessionalHelpScreen ->
                    onNavigate(OnboardingProfessionalHelpScreen())

                OnboardingMoodRateEvent.OnGoBack -> onGoBack()
            }

        return OnboardingMoodRateState(
            moodRatePadding = screenDimensions.moodRatePadding,
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