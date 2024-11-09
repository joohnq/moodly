package com.joohnq.moodapp.view.screens.stresslevel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.PanelContentLight
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.Title
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.stress_analysis
import moodapp.composeapp.generated.resources.stress_level
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelScreenUI(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    stressLevel: StressLevel,
    onContinue: () -> Unit = {},
    onGoBack: () -> Unit = {},
) {
    Scaffold(
        containerColor = Colors.Brown10,
    ) { scaffoldPadding ->
        val padding = PaddingValues(
            top = scaffoldPadding.calculateTopPadding(),
            bottom = scaffoldPadding.calculateBottomPadding() + 100.dp,
            start = scaffoldPadding.calculateStartPadding(LayoutDirection.Ltr),
            end = scaffoldPadding.calculateEndPadding(LayoutDirection.Rtl)
        )
        Column {
            PanelContentLight(
                padding = padding,
                text = Res.string.stress_level,
                background = Drawables.Images.StressLevelBackground,
                backgroundColor = stressLevel.palette.pageBackgroundColor,
                color = stressLevel.palette.pageColor,
                onAdd = onContinue,
                onGoBack = onGoBack
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                        .padding(top = padding.calculateTopPadding()).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = stressLevel.level.toString(), style = TextStyles.FreudScreenScore())
                    Text(
                        text = stringResource(stressLevel.text),
                        style = TextStyles.FreudScreenTitle()
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Title(Res.string.stress_analysis)
        }
    }
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
        stressLevel = stressLevelState.items.getValue().first().stressLevel,
        onContinue = {
        },
        onGoBack = navigation::popBackStack
    )
}

@Preview
@Composable
fun StressRateScreenPreview() {
    StressLevelScreenUI(
        stressLevel = StressLevel.One
    )
}

@Preview
@Composable
fun StressRateScreenPreview2() {
    StressLevelScreenUI(
        stressLevel = StressLevel.Two
    )
}

@Preview
@Composable
fun StressRateScreenPreview3() {
    StressLevelScreenUI(
        stressLevel = StressLevel.Three
    )
}

@Preview
@Composable
fun StressRateScreenPreview4() {
    StressLevelScreenUI(
        stressLevel = StressLevel.Four
    )
}

@Preview
@Composable
fun StressRateScreenPreview5() {
    StressLevelScreenUI(
        stressLevel = StressLevel.Five
    )
}

