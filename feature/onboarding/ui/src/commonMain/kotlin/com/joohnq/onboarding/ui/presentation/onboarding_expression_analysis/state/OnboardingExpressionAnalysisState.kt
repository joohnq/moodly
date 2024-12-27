package com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis.event.OnboardingExpressionEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent

data class OnboardingExpressionAnalysisState(
    val snackBarState: SnackbarHostState,
    val desc: String,
    val onEvent: (OnboardingExpressionEvent) -> Unit = {},
    val onAction: (OnboardingViewModelIntent) -> Unit = {}
)