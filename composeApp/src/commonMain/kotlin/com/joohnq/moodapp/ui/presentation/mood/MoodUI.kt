package com.joohnq.moodapp.ui.presentation.mood

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.ui.components.MoodBarStatistic
import com.joohnq.moodapp.ui.components.MoodFace
import com.joohnq.moodapp.ui.components.PreviousNextButton
import com.joohnq.moodapp.ui.components.SharedPanelComponent
import com.joohnq.moodapp.ui.components.TextWithBackground
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.mood.event.MoodEvent
import com.joohnq.moodapp.ui.presentation.mood.state.MoodState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.Drawables
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.util.helper.DatetimeManager
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.description
import moodapp.composeapp.generated.resources.mood
import moodapp.composeapp.generated.resources.your_mood_is
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodUI(
    state: MoodState,
) {
    val (statsRecord, statsRecords, hasNext, hasPrevious, onEvent) = state
    SharedPanelComponent(
        isDark = true,
        onGoBack = { onEvent(MoodEvent.OnGoBack) },
        backgroundColor = statsRecord.mood.palette.backgroundColor,
        backgroundImage = Drawables.Images.MoodBackground,
        panelTitle = Res.string.mood,
        bodyTitle = Res.string.description,
        color = statsRecord.mood.palette.subColor,
        onAdd = { onEvent(MoodEvent.OnAddStatScreen) },
        topBarContent = {
            TextWithBackground(
                text = DatetimeManager.formatDateTime(statsRecord.date),
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
                        onClick = { onEvent(MoodEvent.OnPrevious) },
                        color = statsRecord.mood.palette.color
                    )
                    MoodFace(
                        modifier = Modifier.size(96.dp),
                        mood = statsRecord.mood
                    )
                    PreviousNextButton(
                        enabled = hasNext,
                        isPrevious = false,
                        onClick = { onEvent(MoodEvent.OnNext) },
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
                    VerticalSpacer(40.dp)
                    MoodBarStatistic(
                        statsRecords = statsRecords,
                        currentStatsRecord = statsRecord,
                        onClick = { onEvent(MoodEvent.OnSetMood(it)) }
                    )
                    VerticalSpacer(20.dp)
                }
            }
        }
    )
}