package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import com.joohnq.moodapp.ui.components.ExpressionAnalysisTextField
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingBaseComponent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis.event.OnboardingExpressionEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis.state.OnboardingExpressionAnalysisState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.util.constants.TestConstants
import com.joohnq.moodapp.viewmodel.OnboardingIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.expression_analysis_desc
import moodapp.composeapp.generated.resources.expression_analysis_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingExpressionAnalysisUI(
    state: OnboardingExpressionAnalysisState,
) {
    val (snackBarState, desc, onEvent, onAction) = state

    OnboardingBaseComponent(
        page = 7,
        snackBarState = snackBarState,
        title = Res.string.expression_analysis_title,
        isContinueButtonVisible = desc.isNotEmpty(),
        onContinue = { onEvent(OnboardingExpressionEvent.OnContinue) },
        onGoBack = { onEvent(OnboardingExpressionEvent.OnGoBack) },
    ) {
        Text(
            text = stringResource(Res.string.expression_analysis_desc),
            style = TextStyles.ParagraphMd(),
            color = Colors.Brown100Alpha64,
            textAlign = TextAlign.Center
        )
        ExpressionAnalysisTextField(
            modifier = Modifier.testTag(TestConstants.TEXT_INPUT),
            text = desc,
            onValueChange = { onAction(OnboardingIntent.UpdateStatsRecordDescription(it)) }
        )
    }
}