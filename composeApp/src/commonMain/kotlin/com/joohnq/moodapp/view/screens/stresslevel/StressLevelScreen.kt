package com.joohnq.moodapp.view.screens.stresslevel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.StressLevelRecord
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.SharedItem
import com.joohnq.moodapp.view.components.StressLevelChart
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.stress_analysis
import moodapp.composeapp.generated.resources.stress_level
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelScreenUI(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    stressLevelRecords: List<StressLevelRecord>,
    onAction: (StressLevelAction) -> Unit = {}
) {
    val first = stressLevelRecords.first().stressLevel
    SharedItem(
        isDark = false,
        onGoBack = { onAction(StressLevelAction.OnGoBack) },
        backgroundColor = first.palette.pageBackgroundColor,
        backgroundImage = Drawables.Images.StressLevelBackground,
        panelTitle = Res.string.stress_level,
        bodyTitle = Res.string.stress_analysis,
        color = first.palette.pageColor,
        onAdd = { onAction(StressLevelAction.OnAdd) },
        panelContent = {
            Column(
                modifier = Modifier.paddingHorizontalMedium()
                    .padding(top = it.calculateTopPadding()).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = first.level.toString(),
                    style = TextStyles.DisplayMdExtraBold(),
                    color = Colors.White
                )
                Text(
                    text = stringResource(first.text),
                    style = TextStyles.TextXlSemiBold(),
                    color = Colors.White
                )
            }
        },
        content = {
            item {
                StressLevelChart(stressLevelRecords = stressLevelRecords)
            }
        }
    )
}

@Composable
fun StressLevelScreen(
    navigation: NavController,
    stressLevelViewModel: StressLevelViewModel = sharedViewModel(),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()

    StressLevelScreenUI(
        snackBarHostState = snackBarHostState,
        stressLevelRecords = stressLevelState.items.getValue(),
        onAction = { action ->
            when (action) {
                is StressLevelAction.OnAdd -> {}
                is StressLevelAction.OnGoBack -> navigation.popBackStack()
            }
        }
    )
}

@Preview
@Composable
fun StressRateScreenPreview() {
    StressLevelScreenUI(
        stressLevelRecords = listOf(
            StressLevelRecord.init().copy(
                stressLevel = StressLevel.One
            )
        )
    )
}

@Preview
@Composable
fun StressRateScreenPreview2() {
    StressLevelScreenUI(
        stressLevelRecords = listOf(
            StressLevelRecord.init().copy(
                stressLevel = StressLevel.Two
            )
        )
    )
}

@Preview
@Composable
fun StressRateScreenPreview3() {
    StressLevelScreenUI(
        stressLevelRecords = listOf(
            StressLevelRecord.init().copy(
                stressLevel = StressLevel.Three
            )
        )
    )
}

@Preview
@Composable
fun StressRateScreenPreview4() {
    StressLevelScreenUI(
        stressLevelRecords = listOf(
            StressLevelRecord.init().copy(
                stressLevel = StressLevel.Four
            )
        )
    )
}

@Preview
@Composable
fun StressRateScreenPreview5() {
    StressLevelScreenUI(
        stressLevelRecords = listOf(
            StressLevelRecord.init().copy(
                stressLevel = StressLevel.Five
            )
        )
    )
}

