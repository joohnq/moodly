package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis.event.OnboardingExpressionEvent
import com.joohnq.moodapp.viewmodel.OnboardingIntent

data class OnboardingExpressionAnalysisState(
    val snackBarState: SnackbarHostState,
    val desc: String,
    val onEvent: (OnboardingExpressionEvent) -> Unit = {},
    val onAction: (OnboardingIntent) -> Unit = {}
)