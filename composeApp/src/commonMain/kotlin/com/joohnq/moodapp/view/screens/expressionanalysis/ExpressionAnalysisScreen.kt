package com.joohnq.moodapp.view.screens.expressionanalysis

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.ExpressionAnalysisTextField
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.TopBarDark
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToHomeGraph
import com.joohnq.moodapp.view.screens.add.AddMoodViewModel
import com.joohnq.moodapp.view.state.UiState.Companion.fold
import com.joohnq.moodapp.viewmodel.StatsViewModel
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.continue_word
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
            modifier = Modifier.padding(padding).padding(horizontal = 16.dp).fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBarDark(onGoBack = onGoBack)
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = stringResource(Res.string.expression_analysis_title),
                style = TextStyles.OnboardingScreenTitle()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                stringResource(Res.string.expression_analysis_desc),
                style = TextStyles.ExpressionAnalysisDesc()
            )
            ExpressionAnalysisTextField(
                modifier = Modifier.testTag(TestConstants.TEXT_INPUT),
                desc,
                onValueChange = setDesc
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (desc.isNotEmpty())
                ButtonWithArrowRight(
                    modifier = Modifier.fillMaxWidth().testTag(TestConstants.CONTINUE_BUTTON),
                    text = Res.string.continue_word,
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