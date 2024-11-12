package com.joohnq.moodapp.view.screens.mood

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joohnq.moodapp.MoodsManager
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.helper.DatetimeHelper
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.PreviousNextButton
import com.joohnq.moodapp.view.components.SharedItem
import com.joohnq.moodapp.view.components.TextWithBackground
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.StatsViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.description
import moodapp.composeapp.generated.resources.mood
import moodapp.composeapp.generated.resources.your_mood_is
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodScreenUi(
    statsRecord: StatsRecord,
    hasNext: Boolean,
    hasPrevious: Boolean,
    onAction: (MoodAction) -> Unit = {},
) {
    SharedItem(
        isDark = true,
        onGoBack = { onAction(MoodAction.GoBack) },
        backgroundColor = statsRecord.mood.palette.backgroundColor,
        backgroundImage = Drawables.Images.MoodBackground,
        panelTitle = Res.string.mood,
        bodyTitle = Res.string.description,
        color = statsRecord.mood.palette.subColor,
        onAdd = { onAction(MoodAction.OnAdd) },
        topBarContent = {
            TextWithBackground(
                text = DatetimeHelper.formatLocalDateTime(statsRecord.date),
                textColor = statsRecord.mood.palette.moodScreenMoodFaceColor,
                backgroundColor = statsRecord.mood.palette.subColor,
            )
        },
        panelContent = {
            Column(
                modifier = Modifier.paddingHorizontalMedium()
                    .padding(top = it.calculateTopPadding()).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.your_mood_is),
                    style = TextStyles.TextXlBold(),
                    color = statsRecord.mood.palette.moodScreenMoodFaceColor
                )
                Text(
                    text = stringResource(statsRecord.mood.text),
                    style = TextStyles.Heading2xlExtraBold(),
                    color = statsRecord.mood.palette.moodScreenMoodFaceColor
                )
                VerticalSpacer(10.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PreviousNextButton(
                        enabled = hasPrevious,
                        isPrevious = true,
                        onClick = { onAction(MoodAction.Previous) },
                        color = statsRecord.mood.palette.color
                    )
                    MoodFace(
                        modifier = Modifier.size(96.dp),
                        mood = statsRecord.mood
                    )
                    PreviousNextButton(
                        enabled = hasNext,
                        isPrevious = false,
                        onClick = { onAction(MoodAction.Next) },
                        color = statsRecord.mood.palette.color
                    )
                }
            }
        },
        content = {
            item {
                Column {
                    Text(
                        text = statsRecord.description,
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Brown100Alpha64,
                        modifier = Modifier.fillMaxWidth().paddingHorizontalMedium()
                    )
                }
            }
        }
    )
}

@Composable
fun MoodScreen(
    statsRecord: StatsRecord,
    statsViewModel: StatsViewModel = sharedViewModel(),
    navigation: NavHostController
) {
    val statsRecords by statsViewModel.statsRecords.collectAsState()
    var hasNext by remember { mutableStateOf<StatsRecord?>(null) }
    var hasPrevious by remember { mutableStateOf<StatsRecord?>(null) }
    var currentStatsRecord by remember { mutableStateOf(statsRecord) }

    LaunchedEffect(currentStatsRecord) {
        hasNext = MoodsManager.getNext(currentStatsRecord, statsRecords.getValue())
        hasPrevious =
            MoodsManager.getPrevious(currentStatsRecord, statsRecords.getValue())
    }

    MoodScreenUi(
        statsRecord = currentStatsRecord,
        hasNext = hasNext != null,
        hasPrevious = hasPrevious != null,
        onAction = {
            when (it) {
                MoodAction.GoBack -> navigation.popBackStack()
                MoodAction.Next -> hasNext?.run { currentStatsRecord = this }
                MoodAction.Previous -> hasPrevious?.run { currentStatsRecord = this }
                MoodAction.OnAdd -> {}
            }
        },
    )
}

@Preview
@Composable
fun MoodScreenPreview() {
    MoodScreenUi(
        statsRecord = StatsRecord.init().copy(mood = Mood.Overjoyed),
        hasPrevious = true,
        hasNext = true
    )
}

@Preview
@Composable
fun MoodScreenPreview2() {
    MoodScreenUi(
        statsRecord = StatsRecord.init().copy(mood = Mood.Happy),
        hasPrevious = true,
        hasNext = true
    )
}

@Preview
@Composable
fun MoodScreenPreview3() {
    MoodScreenUi(
        statsRecord = StatsRecord.init().copy(mood = Mood.Neutral),
        hasPrevious = true,
        hasNext = true
    )
}

@Preview
@Composable
fun MoodScreenPreview4() {
    MoodScreenUi(
        statsRecord = StatsRecord.init().copy(mood = Mood.Sad),
        hasPrevious = true,
        hasNext = true
    )
}

@Preview
@Composable
fun MoodScreenPreview5() {
    MoodScreenUi(
        statsRecord = StatsRecord.init().copy(mood = Mood.Depressed),
        hasPrevious = true,
        hasNext = true
    )
}

