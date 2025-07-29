package com.joohnq.onboarding.impl.ui.presentation.onboarding_stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingStressLevelScreen(
    onNavigateToExpressionAnalysis: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: OnboardingViewModel = sharedViewModel()
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.OnNavigateToNext -> onNavigateToExpressionAnalysis()
            OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingStressLevelContent(
        state = state.stressLevel,
        onAction = viewModel::onIntent,
        onEvent = ::onEvent
    )
}