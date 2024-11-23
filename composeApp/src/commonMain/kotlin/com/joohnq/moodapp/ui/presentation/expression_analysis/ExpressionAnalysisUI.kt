package com.joohnq.moodapp.ui.presentation.expression_analysis

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.ui.components.ContinueButton
import com.joohnq.moodapp.ui.components.ExpressionAnalysisTextField
import com.joohnq.moodapp.ui.components.TopBar
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.expression_analysis.event.ExpressionAnalysisEvent
import com.joohnq.moodapp.ui.presentation.expression_analysis.state.ExpressionAnalysisState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalSmall
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.util.constants.TestConstants
import com.joohnq.moodapp.viewmodel.StatsIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.expression_analysis_desc
import moodapp.composeapp.generated.resources.expression_analysis_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExpressionAnalysisUI(
    state: ExpressionAnalysisState,
) {
    val (snackBarState, description, onAction, onEvent) = state
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarState) },
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).paddingHorizontalSmall().fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(onGoBack = { onEvent(ExpressionAnalysisEvent.OnGoBack) })
            VerticalSpacer(60.dp)
            Text(
                text = stringResource(Res.string.expression_analysis_title),
                style = TextStyles.HeadingSmExtraBold(),
                color = Colors.Brown80
            )
            VerticalSpacer(24.dp)
            Text(
                text = stringResource(Res.string.expression_analysis_desc),
                style = TextStyles.ParagraphMd(),
                color = Colors.Brown100Alpha64,
                textAlign = TextAlign.Center
            )
            ExpressionAnalysisTextField(
                text = description,
                onValueChange = { onAction(StatsIntent.UpdateAddingStatsRecordDescription(it)) }
            )
            VerticalSpacer(24.dp)
            if (description.isNotEmpty())
                ContinueButton(
                    modifier = Modifier.fillMaxWidth().testTag(TestConstants.CONTINUE_BUTTON),
                    onClick = { onEvent(ExpressionAnalysisEvent.OnNavigateToAddStatsRecord) }
                )
        }
    }
}