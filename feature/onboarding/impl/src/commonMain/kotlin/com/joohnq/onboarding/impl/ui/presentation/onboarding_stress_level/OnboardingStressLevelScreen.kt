package com.joohnq.onboarding.impl.ui.presentation.onboarding_stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.event.OnboardingEvent
import com.joohnq.onboarding.impl.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingStressLevelScreen(
    onNavigateToExpressionAnalysis: () -> Unit,
    onGoBack: () -> Unit
) {
    val onboardingViewModel: com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel = sharedViewModel()
    val onboardingState by onboardingViewModel.state.collectAsState()

    fun onEvent(event: com.joohnq.onboarding.impl.ui.event.OnboardingEvent) =
        when (event) {
            _root_ide_package_.com.joohnq.onboarding.impl.ui.event.OnboardingEvent.OnNavigateToNext -> onNavigateToExpressionAnalysis()
            _root_ide_package_.com.joohnq.onboarding.impl.ui.event.OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingStressLevelContent(
        state = onboardingState.stressLevel,
        onAction = onboardingViewModel::onIntent,
        onEvent = ::onEvent
    )
}