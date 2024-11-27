package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis.OnboardingExpressionAnalysisScreen
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_stress_level.event.OnboardingStressLevelEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_stress_level.state.OnboardingStressLevelState
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingViewModel

class OnboardingStressLevelScreen : CustomScreen<OnboardingStressLevelState>() {
    @Composable
    override fun Screen(): OnboardingStressLevelState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.onboardingState.collectAsState()

        fun onEvent(event: OnboardingStressLevelEvent) =
            when (event) {
                OnboardingStressLevelEvent.OnNavigateToOnboardingExpressionAnalysisScreen ->
                    onNavigate(OnboardingExpressionAnalysisScreen())

                OnboardingStressLevelEvent.OnGoBack -> onGoBack()
            }

        return OnboardingStressLevelState(
            selectedOption = onboardingState.stressLevel,
            onAction = onboardingViewModel::onAction,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: OnboardingStressLevelState) = OnboardingStressLevelUI(state)
}
