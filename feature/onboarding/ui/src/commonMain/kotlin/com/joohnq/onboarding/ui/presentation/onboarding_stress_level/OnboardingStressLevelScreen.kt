package com.joohnq.onboarding.ui.presentation.onboarding_stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.CustomScreen
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.presentation.onboarding_stress_level.state.OnboardingStressLevelState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel

class OnboardingStressLevelScreen(
    private val onNavigateToExpressionAnalysis: () -> Unit,
    private val onGoBack: () -> Unit,
) :
    CustomScreen<OnboardingStressLevelState>() {
    @Composable
    override fun Screen(): OnboardingStressLevelState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.state.collectAsState()

        fun onEvent(event: OnboardingEvent) =
            when (event) {
                OnboardingEvent.OnNavigateToNext -> onNavigateToExpressionAnalysis()
                OnboardingEvent.OnGoBack -> onGoBack()
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
