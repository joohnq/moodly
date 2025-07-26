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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.mood.impl.ui.presentation.add_mood.AddMoodContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.input_field.ExpressionAnalysisTextField
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.continue_word
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
    onAddAction: (AddMoodContract.Intent) -> Unit = {},
    onEvent: (ExpressionAnalysisContract.Event) -> Unit = {}
) {
    AppScaffoldLayout(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackBarHostState = snackBarState
    ) { padding ->
        Column(
            modifier =
            Modifier
                .padding(padding)
                .paddingHorizontalMedium()
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTopBar(onGoBack = { onEvent(ExpressionAnalysisContract.Event.OnGoBack) })
            VerticalSpacer(60.dp)
            Text(
                text = stringResource(Res.string.expression_analysis_title),
                style = TextStyles.headingSmExtraBold(),
                color = Colors.Brown80
            )
            VerticalSpacer(24.dp)
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
                    onAddAction(
                        AddMoodContract.Intent.UpdateAddingMoodRecordDescription(it)
                    )
                }
            )
            VerticalSpacer(24.dp)
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = Res.string.continue_word,
                enabled = description.isNotEmpty(),
                onClick = { onEvent(ExpressionAnalysisContract.Event.OnAdd) }
            )
        }
    }
}
