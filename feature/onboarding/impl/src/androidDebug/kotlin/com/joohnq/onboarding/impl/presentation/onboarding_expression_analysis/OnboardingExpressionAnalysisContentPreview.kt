package com.joohnq.onboarding.impl.presentation.onboarding_expression_analysis

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnboardingExpressionAnalysisContentPreview() {
    OnboardingExpressionAnalysisContent(
        description = "Description",
    )
}

@Preview
@Composable
fun OnboardingExpressionAnalysisContentEmptyPreview() {
    OnboardingExpressionAnalysisContent(
        description = "",
    )
}