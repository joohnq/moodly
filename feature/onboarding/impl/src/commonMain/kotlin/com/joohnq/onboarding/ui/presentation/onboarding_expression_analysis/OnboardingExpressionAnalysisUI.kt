package com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.ui.viewmodel.OnboardingIntent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.ExpressionAnalysisTextField
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.expression_analysis_desc
import com.joohnq.shared_resources.expression_analysis_title
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OnboardingExpressionAnalysisUI(
    snackBarState: SnackbarHostState,
    description: String,
    onEvent: (OnboardingEvent) -> Unit = {},
    onAction: (OnboardingIntent) -> Unit = {},
) {
    OnboardingBaseComponent(
        page = 7,
        snackBarState = snackBarState,
        title = Res.string.expression_analysis_title,
        isContinueButtonVisible = description.isNotEmpty(),
        onContinue = { onEvent(OnboardingEvent.OnNavigateToNext) },
        onGoBack = { onEvent(OnboardingEvent.OnGoBack) },
    ) {
        Text(
            text = stringResource(Res.string.expression_analysis_desc),
            style = TextStyles.ParagraphMd(),
            color = Colors.Brown100Alpha64,
            textAlign = TextAlign.Center
        )
        VerticalSpacer(24.dp)
        ExpressionAnalysisTextField(
            text = description,
            onValueChange = {
                onAction(
                    OnboardingIntent.UpdateMoodRecordDescription(it)
                )
            }
        )
    }
}

@Preview
@Composable
fun OnboardingExpressionAnalysisUIPreview() {
    OnboardingExpressionAnalysisUI(
        snackBarState = SnackbarHostState(),
        description = "",
        onEvent = {},
        onAction = {},
    )
}