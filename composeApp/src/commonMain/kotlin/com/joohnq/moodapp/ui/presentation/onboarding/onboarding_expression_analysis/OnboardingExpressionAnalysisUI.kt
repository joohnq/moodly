package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.ui.components.ExpressionAnalysisTextField
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingBaseComponent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis.event.OnboardingExpressionEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis.state.OnboardingExpressionAnalysisState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.viewmodel.OnboardingIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.expression_analysis_desc
import moodapp.composeapp.generated.resources.expression_analysis_title
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
        Column(modifier = Modifier.paddingHorizontalMedium()) {
            Text(
                text = stringResource(Res.string.expression_analysis_desc),
                style = TextStyles.ParagraphMd(),
                color = Colors.Brown100Alpha64,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(10.dp)
            ExpressionAnalysisTextField(
                modifier = Modifier.testTag(OnboardingExpressionAnalysisScreen.OnboardingExpressionTestTag.TEXT_INPUT),
                text = state.desc,
                onValueChange = { state.onAction(OnboardingIntent.UpdateStatsRecordDescription(it)) }
            )
        }
    }
}