package com.joohnq.onboarding.impl.presentation.onboarding_expression_analysis

import androidx.compose.runtime.Composable
import com.joohnq.onboarding.impl.ui.presentation.onboarding_expression_analysis.OnboardingExpressionAnalysisContent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    OnboardingExpressionAnalysisContent(
        description = "Description"
    )
}

@Preview
@Composable
private fun EmptyPreview() {
    OnboardingExpressionAnalysisContent(
        description = ""
    )
}
