package com.joohnq.moodapp.view.screens.sleepquality

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.SleepQualityRecord
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.SharedItem
import com.joohnq.moodapp.view.components.SleepQualityIndicator
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sleep_quality
import moodapp.composeapp.generated.resources.sleep_quality_level
import moodapp.composeapp.generated.resources.sleep_stats
import org.jetbrains.compose.resources.stringResource

@Composable
fun SleepQualityScreenUI(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    sleepQualityRecords: List<SleepQualityRecord>,
    onContinue: () -> Unit = {},
    onAdd: () -> Unit = {},
    onGoBack: () -> Unit = {}
) {
    val first = sleepQualityRecords.first().sleepQuality
    SharedItem(
        isDark = false,
        onGoBack = onGoBack,
        backgroundColor = first.palette.color,
        backgroundImage = Drawables.Images.SleepQualityBackground,
        panelTitle = Res.string.sleep_quality,
        bodyTitle = Res.string.sleep_stats,
        color = first.palette.backgroundColor,
        onAdd = onAdd,
        items = sleepQualityRecords,
        panelContent = {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
                    .padding(top = it.calculateTopPadding()).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.sleep_quality_level, first.level),
                    style = TextStyles.Heading2xlExtraBold(),
                    color = Colors.White
                )
                VerticalSpacer(10.dp)
                Text(
                    text = stringResource(first.firstText),
                    style = TextStyles.HeadingSmExtraBold(),
                    color = Colors.White
                )
                VerticalSpacer(20.dp)
                SleepQualityIndicator(sleepQuality = first)
            }
        },
        content = {

        }
    )
}

@Composable
fun SleepQualityScreen(
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel(),
    navigation: NavController
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val sleepQualityItems by sleepQualityViewModel.items.collectAsState()

    SleepQualityScreenUI(
        snackBarHostState = snackBarHostState,
        onGoBack = navigation::popBackStack,
        sleepQualityRecords = sleepQualityItems.getValue(),
        onContinue = {
        }
    )
}

@Preview
@Composable
fun SleepQualityScreenPreview() {
    SleepQualityScreenUI(
        sleepQualityRecords = listOf(
            SleepQualityRecord.init().copy(
                sleepQuality = SleepQuality.Worst
            )
        )
    )
}

@Preview
@Composable
fun SleepQualityScreenPreview2() {
    SleepQualityScreenUI(
        sleepQualityRecords = listOf(
            SleepQualityRecord.init().copy(
                sleepQuality = SleepQuality.Poor
            ),
            SleepQualityRecord.init().copy(
                sleepQuality = SleepQuality.Worst
            ),
            SleepQualityRecord.init().copy(
                sleepQuality = SleepQuality.Poor
            ),
            SleepQualityRecord.init().copy(
                sleepQuality = SleepQuality.Excellent
            )
        )
    )
}

@Preview
@Composable
fun SleepQualityScreenPreview3() {
    SleepQualityScreenUI(
        sleepQualityRecords = listOf(
            SleepQualityRecord.init().copy(
                sleepQuality = SleepQuality.Fair
            )
        )
    )
}

@Preview
@Composable
fun SleepQualityScreenPreview4() {
    SleepQualityScreenUI(
        sleepQualityRecords = listOf(
            SleepQualityRecord.init().copy(
                sleepQuality = SleepQuality.Good
            )
        )
    )
}

@Preview
@Composable
fun SleepQualityScreenPreview5() {
    SleepQualityScreenUI(
        sleepQualityRecords = listOf(
            SleepQualityRecord.init().copy(
                sleepQuality = SleepQuality.Excellent
            )
        )
    )
}

