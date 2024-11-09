package com.joohnq.moodapp.view.screens.sleepquality

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
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.PanelContentLight
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.Title
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sleep_quality
import moodapp.composeapp.generated.resources.sleep_stats
import org.jetbrains.compose.resources.stringResource

@Composable
fun SleepQualityScreenUI(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    sleepQuality: SleepQuality,
    onContinue: () -> Unit = {},
    onGoBack: () -> Unit = {}
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
                text = Res.string.sleep_quality,
                background = Drawables.Images.SleepQualityBackground,
                backgroundColor = sleepQuality.palette.color,
                color = sleepQuality.palette.backgroundColor,
                onAdd = onContinue,
                onGoBack = onGoBack
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                        .padding(top = padding.calculateTopPadding()).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = sleepQuality.level.toString(),
                        style = TextStyles.FreudScreenScore()
                    )
                    Text(
                        text = stringResource(sleepQuality.firstText),
                        style = TextStyles.FreudScreenTitle()
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Title(Res.string.sleep_stats)
        }
    }
}

@Composable
fun SleepQualityScreen(
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel(),
    navigation: NavController
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()

    SleepQualityScreenUI(
        snackBarHostState = snackBarHostState,
        sleepQuality = sleepQualityState.items.getValue().first().sleepQuality,
        onGoBack = navigation::popBackStack,
        onContinue = {
        }
    )
}

@Preview
@Composable
fun SleepQualityScreenPreview() {
    SleepQualityScreenUI(
        sleepQuality = SleepQuality.Worst
    )
}

@Preview
@Composable
fun SleepQualityScreenPreview2() {
    SleepQualityScreenUI(
        sleepQuality = SleepQuality.Poor
    )
}

@Preview
@Composable
fun SleepQualityScreenPreview3() {
    SleepQualityScreenUI(
        sleepQuality = SleepQuality.Fair
    )
}

@Preview
@Composable
fun SleepQualityScreenPreview4() {
    SleepQualityScreenUI(
        sleepQuality = SleepQuality.Good
    )
}

@Preview
@Composable
fun SleepQualityScreenPreview5() {
    SleepQualityScreenUI(
        sleepQuality = SleepQuality.Excellent
    )
}

