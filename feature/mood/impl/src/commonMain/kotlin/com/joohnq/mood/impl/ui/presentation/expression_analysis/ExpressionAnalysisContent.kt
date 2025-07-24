package com.joohnq.mood.impl.ui.presentation.expression_analysis

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.mood.impl.ui.presentation.add_mood.viewmodel.AddMoodIntent
import com.joohnq.mood.impl.ui.presentation.expression_analysis.event.ExpressionAnalysisEvent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.*
import com.joohnq.shared_resources.components.button.ContinueButton
import com.joohnq.shared_resources.expression_analysis_desc
import com.joohnq.shared_resources.expression_analysis_title
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExpressionAnalysisContent(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    description: String,
    onAddAction: (AddMoodIntent) -> Unit = {},
    onEvent: (ExpressionAnalysisEvent) -> Unit = {},
) {
    ScaffoldSnackBar(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackBarHostState = snackBarState
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).paddingHorizontalMedium().fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTopBar(onGoBack = { onEvent(ExpressionAnalysisEvent.OnGoBack) })
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
                text = description,
                onValueChange = {
                    onAddAction(
                        AddMoodIntent.UpdateAddingMoodRecordDescription(it)
                    )
                }
            )
            VerticalSpacer(24.dp)
            if (description.isNotEmpty())
                ContinueButton(
                    modifier = Modifier.fillMaxWidth().testTag("CONTINUE_BUTTON"),
                    onClick = { onEvent(ExpressionAnalysisEvent.OnAdd) }
                )
        }
    }
}