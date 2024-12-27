package com.joohnq.mood.ui.presentation.expression_analysis

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
import com.joohnq.mood.components.ContinueButton
import com.joohnq.mood.components.ExpressionAnalysisTextField
import com.joohnq.mood.components.TopBar
import com.joohnq.mood.components.VerticalSpacer
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.mood.theme.TextStyles
import com.joohnq.mood.ui.presentation.add_stats.AddStatIntent
import com.joohnq.mood.ui.presentation.expression_analysis.event.ExpressionAnalysisEvent
import com.joohnq.mood.ui.presentation.expression_analysis.state.ExpressionAnalysisState
import com.joohnq.mood.util.constants.TestConstants
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.expression_analysis_desc
import com.joohnq.shared.ui.expression_analysis_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExpressionAnalysisUI(
    state: ExpressionAnalysisState,
) {
    Scaffold(
        snackbarHost = { SnackbarHost(state.snackBarState) },
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).paddingHorizontalMedium().fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(onGoBack = { state.onEvent(ExpressionAnalysisEvent.OnGoBack) })
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
            VerticalSpacer(24.dp)
            ExpressionAnalysisTextField(
                text = state.description,
                onValueChange = {
                    state.onAddAction(
                        AddStatIntent.UpdateAddingStatsRecordDescription(it)
                    )
                }
            )
            VerticalSpacer(24.dp)
            if (state.description.isNotEmpty())
                ContinueButton(
                    modifier = Modifier.fillMaxWidth().testTag(TestConstants.CONTINUE_BUTTON),
                    onClick = { state.onEvent(ExpressionAnalysisEvent.OnAdd) }
                )
        }
    }
}