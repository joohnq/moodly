package com.joohnq.onboarding.ui.presentation.onboarding_stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.ui.presentation.onboarding_stress_level.event.OnboardingStressLevelEvent
import com.joohnq.onboarding.ui.presentation.onboarding_stress_level.state.OnboardingStressLevelState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel

class OnboardingStressLevelScreen : CustomScreen<OnboardingStressLevelState>() {
    @Composable
    override fun Screen(): OnboardingStressLevelState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.state.collectAsState()

        fun onEvent(event: OnboardingStressLevelEvent) =
            when (event) {
                OnboardingStressLevelEvent.OnNavigateToOnboardingExpressionAnalysisScreen -> {}
//                    onNavigate(OnboardingExpressionAnalysisScreen())

                OnboardingStressLevelEvent.OnGoBack -> {}
//                    onGoBack()
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
