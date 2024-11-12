package com.joohnq.moodapp.view.screens.expressionanalysis

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ContinueButton
import com.joohnq.moodapp.view.components.ExpressionAnalysisTextField
import com.joohnq.moodapp.view.components.TopBar
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToHomeGraph
import com.joohnq.moodapp.view.state.UiState.Companion.fold
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalSmall
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.AddMoodViewModel
import com.joohnq.moodapp.viewmodel.StatsViewModel
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.expression_analysis_desc
import moodapp.composeapp.generated.resources.expression_analysis_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExpressionAnalysisScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    desc: String,
    setDesc: (String) -> Unit = {},
    onGoBack: () -> Unit = {},
    onContinue: () -> Unit = {}
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarState) },
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).paddingHorizontalSmall().fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(onGoBack = onGoBack)
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
                color = Colors.Brown100Alpha64
            )
            ExpressionAnalysisTextField(
                text = desc,
                onValueChange = setDesc
            )
            VerticalSpacer(24.dp)
            if (desc.isNotEmpty())
                ContinueButton(
                    modifier = Modifier.fillMaxWidth().testTag(TestConstants.CONTINUE_BUTTON),
                    onClick = onContinue
                )
        }
    }
}

@Composable
fun ExpressionAnalysisScreen(
    navigation: NavController,
    addMoodViewModel: AddMoodViewModel = sharedViewModel(),
    statsViewModel: StatsViewModel = sharedViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    val addMoodState by addMoodViewModel.addMoodState.collectAsState()
    val addingStatus by statsViewModel.addingStatus.collectAsState()

    LaunchedEffect(addingStatus) {
        addingStatus.fold(
            onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
            onSuccess = {
                statsViewModel.resetAddingStatus()
                navigation.onNavigateToHomeGraph()
            },
        )
    }

    ExpressionAnalysisScreenUI(
        snackBarState = snackBarState,
        onGoBack = navigation::popBackStack,
        desc = addMoodState.statsRecord.description,
        setDesc = addMoodViewModel::updateStatsRecordDescription,
        onContinue = { statsViewModel.addStatsRecord(addMoodState.statsRecord) }
    )
}

@Preview
@Composable
fun ExpressionAnalysisScreenPreview() {
    ExpressionAnalysisScreenUI(
        desc = ""
    )
}