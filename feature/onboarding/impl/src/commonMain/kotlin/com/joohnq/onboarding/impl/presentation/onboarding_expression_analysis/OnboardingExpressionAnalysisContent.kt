package com.joohnq.onboarding.impl.presentation.onboarding_expression_analysis

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.onboarding.impl.event.OnboardingEvent
import com.joohnq.onboarding.impl.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.impl.viewmodel.OnboardingContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.input_field.ExpressionAnalysisTextField
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.expression_analysis_desc
import com.joohnq.shared_resources.expression_analysis_title
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingExpressionAnalysisContent(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    description: String,
    onEvent: (OnboardingEvent) -> Unit = {},
    onAction: (OnboardingContract.Intent) -> Unit = {},
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
            style = TextStyles.paragraphMd(),
            color = Colors.Brown100Alpha64,
            textAlign = TextAlign.Center
        )
        VerticalSpacer(24.dp)
        ExpressionAnalysisTextField(
            text = description,
            onValueChange = {
                onAction(
                    OnboardingContract.Intent.UpdateMoodRecordDescription(it)
                )
            }
        )
    }
}