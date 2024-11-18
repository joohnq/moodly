package com.joohnq.moodapp.view.screens.sleepquality

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.joohnq.moodapp.entities.SleepStatsItem
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.SharedPanelComponent
import com.joohnq.moodapp.view.components.SleepQualityCard
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToAddSleepQuality
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.end_sleeping
import moodapp.composeapp.generated.resources.mood
import moodapp.composeapp.generated.resources.sleep_quality
import moodapp.composeapp.generated.resources.sleep_quality_level
import moodapp.composeapp.generated.resources.sleep_stats
import moodapp.composeapp.generated.resources.sleeping_influences
import moodapp.composeapp.generated.resources.start_sleeping
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SleepQualityScreenUI(
    sleepQualityRecords: List<SleepQualityRecord>,
    onAction: (SleepQualityAction) -> Unit = {}
) {
    val last = sleepQualityRecords.last()
    val options = remember {
        listOf(
            SleepStatsItem(
                icon = Drawables.Icons.Sleep,
                title = Res.string.start_sleeping,
            ) {
                Text(
                    text = last.startSleeping,
                    style = TextStyles.TextMdSemiBold(),
                    color = Colors.Brown80
                )
            },
            SleepStatsItem(
                icon = Drawables.Icons.Sun,
                title = Res.string.end_sleeping,
            ) {
                Text(
                    text = last.endSleeping,
                    style = TextStyles.TextMdSemiBold(),
                    color = Colors.Brown80
                )
            },
            SleepStatsItem(
                icon = Drawables.Icons.MoodNeutral,
                title = Res.string.mood,
            ) {
                Text(
                    text = stringResource(SleepQuality.toMood(last.sleepQuality).text),
                    style = TextStyles.TextMdSemiBold(),
                    color = Colors.Brown80
                )
            },
            SleepStatsItem(
                icon = Drawables.Icons.Moon,
                title = Res.string.sleeping_influences,
            ) {
                if (last.sleepInfluences.isNotEmpty())
                    Text(
                        text = last.sleepInfluences.joinToString(", "),
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Brown80
                    )
            }
        )
    }

    SharedPanelComponent(
        containerColor = Colors.Brown10,
        isDark = false,
        onGoBack = { onAction(SleepQualityAction.OnGoBack) },
        backgroundColor = last.sleepQuality.palette.color,
        backgroundImage = Drawables.Images.SleepQualityBackground,
        panelTitle = Res.string.sleep_quality,
        bodyTitle = Res.string.sleep_stats,
        color = last.sleepQuality.palette.backgroundColor,
        onAdd = { onAction(SleepQualityAction.OnAdd) },
        panelContent = {
            Column(
                modifier = Modifier
                    .paddingHorizontalMedium()
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.sleep_quality_level, last.sleepQuality.level),
                    style = TextStyles.Heading2xlExtraBold(),
                    color = Colors.White
                )
                VerticalSpacer(10.dp)
                Text(
                    text = stringResource(last.sleepQuality.firstText),
                    style = TextStyles.HeadingSmExtraBold(),
                    color = Colors.White
                )
                VerticalSpacer(20.dp)
            }
        },
        content = {
            item {
                FlowRow(
                    maxItemsInEachRow = 2,
                    maxLines = 2,
                    modifier = Modifier.fillMaxSize().wrapContentHeight()
                        .paddingHorizontalMedium(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    options.forEach { item ->
                        SleepQualityCard(
                            modifier = Modifier.weight(1f),
                            item = item
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun SleepQualityScreen(
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel(),
    navigation: NavController
) {
    val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()

    SleepQualityScreenUI(
        sleepQualityRecords = sleepQualityState.items.getValue(),
        onAction = { action ->
            when (action) {
                SleepQualityAction.OnAdd -> navigation.onNavigateToAddSleepQuality()
                SleepQualityAction.OnGoBack -> navigation.popBackStack()
            }
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

