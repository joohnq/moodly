package com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.mood.components.ExpressionAnalysisTextField
import com.joohnq.mood.components.VerticalSpacer
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.TextStyles
import com.joohnq.onboarding.ui.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis.event.OnboardingExpressionEvent
import com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis.state.OnboardingExpressionAnalysisState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.expression_analysis_desc
import com.joohnq.shared.ui.expression_analysis_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingExpressionAnalysisUI(
    state: OnboardingExpressionAnalysisState,
) {
    OnboardingBaseComponent(
        page = 7,
        snackBarState = state.snackBarState,
        title = Res.string.expression_analysis_title,
        isContinueButtonVisible = state.desc.isNotEmpty(),
        onContinue = { state.onEvent(OnboardingExpressionEvent.OnContinue) },
        onGoBack = { state.onEvent(OnboardingExpressionEvent.OnGoBack) },
    ) {
        Text(
            text = stringResource(Res.string.expression_analysis_desc),
            style = TextStyles.ParagraphMd(),
            color = Colors.Brown100Alpha64,
            textAlign = TextAlign.Center
        )
        VerticalSpacer(24.dp)
        ExpressionAnalysisTextField(
            modifier = Modifier.testTag(OnboardingExpressionAnalysisScreen.OnboardingExpressionTestTag.TEXT_INPUT),
            text = state.desc,
            onValueChange = {
                state.onAction(
                    OnboardingViewModelIntent.UpdateStatsRecordDescription(
                        it
                    )
                )
            }
        )
    }
}