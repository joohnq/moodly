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
    SharedPanelComponent(
        isDark = true,
        onGoBack = { state.onEvent(MoodEvent.OnGoBack) },
        backgroundColor = state.statsRecord.mood.palette.backgroundColor,
        backgroundImage = Drawables.Images.MoodBackground,
        panelTitle = Res.string.mood,
        bodyTitle = Res.string.description,
        color = state.statsRecord.mood.palette.subColor,
        onAdd = { state.onEvent(MoodEvent.OnAddStatScreen) },
        topBarContent = {
            TextWithBackground(
                text = DatetimeManager.formatDateTime(state.statsRecord.date),
                textColor = state.statsRecord.mood.palette.moodScreenMoodFaceColor,
                backgroundColor = state.statsRecord.mood.palette.subColor,
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
                    color = state.statsRecord.mood.palette.moodScreenMoodFaceColor
                )
                Text(
                    text = stringResource(state.statsRecord.mood.text),
                    style = TextStyles.Heading2xlExtraBold(),
                    color = state.statsRecord.mood.palette.moodScreenMoodFaceColor
                )
                VerticalSpacer(10.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PreviousNextButton(
                        enabled = state.hasPrevious,
                        isPrevious = true,
                        onClick = { state.onEvent(MoodEvent.OnPrevious) },
                        color = state.statsRecord.mood.palette.color
                    )
                    MoodFace(
                        modifier = Modifier.size(96.dp),
                        mood = state.statsRecord.mood
                    )
                    PreviousNextButton(
                        enabled = state.hasNext,
                        isPrevious = false,
                        onClick = { state.onEvent(MoodEvent.OnNext) },
                        color = state.statsRecord.mood.palette.color
                    )
                }
            }
        },
        content = {
            item {
                Column {
                    Text(
                        text = state.statsRecord.description,
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Brown100Alpha64,
                        modifier = Modifier.fillMaxWidth().paddingHorizontalMedium()
                    )
                    VerticalSpacer(40.dp)
                    MoodBarStatistic(
                        statsRecords = state.statsRecords,
                        currentStatsRecord = state.statsRecord,
                        onClick = { state.onEvent(MoodEvent.OnSetMood(it)) }
                    )
                    VerticalSpacer(20.dp)
                }
            }
        }
    )
}