package com.joohnq.onboarding.impl.presentation.onboarding_stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel
import com.joohnq.onboarding.impl.event.OnboardingEvent
import com.joohnq.onboarding.impl.viewmodel.OnboardingViewModel

@Composable
fun OnboardingStressLevelScreen(
    onNavigateToExpressionAnalysis: () -> Unit,
    onGoBack: () -> Unit,
) {
    val onboardingViewModel: OnboardingViewModel = sharedViewModel()
    val onboardingState by onboardingViewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.OnNavigateToNext -> onNavigateToExpressionAnalysis()
            OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingStressLevelContent(
        state = onboardingState.stressLevel,
        onAction = onboardingViewModel::onIntent,
        onEvent = ::onEvent
    )
}