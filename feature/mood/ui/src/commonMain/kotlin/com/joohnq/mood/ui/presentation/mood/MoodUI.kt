package com.joohnq.mood.ui.presentation.mood

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.domain.DatetimeProvider
import com.joohnq.mood.components.CircularLoading
import com.joohnq.mood.components.PreviousNextButton
import com.joohnq.mood.components.SharedPanelComponent
import com.joohnq.mood.components.TextWithBackground
import com.joohnq.mood.components.VerticalSpacer
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.state.UiState.Companion.foldComposable
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.Drawables
import com.joohnq.mood.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.mood.theme.TextStyles
import com.joohnq.mood.ui.MoodResource.Companion.toResource
import com.joohnq.mood.ui.components.MoodBarStatistic
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.ui.presentation.mood.state.MoodState
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.description
import com.joohnq.shared.ui.mood
import com.joohnq.shared.ui.your_mood_is
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodUI(
    state: MoodState,
) {
    state.statsRecord?.run {
        val resource = mood.toResource()
        SharedPanelComponent(
            isDark = true,
            onGoBack = { state.onEvent(MoodEvent.OnGoBack) },
            backgroundColor = resource.palette.backgroundColor,
            backgroundImage = Drawables.Images.MoodBackground,
            panelTitle = Res.string.mood,
            bodyTitle = Res.string.description,
            color = resource.palette.subColor,
            onAdd = { state.onEvent(MoodEvent.OnAddStatScreen) },
            topBarContent = {
                TextWithBackground(
                    text = DatetimeProvider.formatDateTime(state.statsRecord.date),
                    textColor = resource.palette.moodScreenMoodFaceColor,
                    backgroundColor = resource.palette.subColor,
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
                        color = resource.palette.moodScreenMoodFaceColor
                    )
                    Text(
                        text = stringResource(resource.text),
                        style = TextStyles.Heading2xlExtraBold(),
                        color = resource.palette.moodScreenMoodFaceColor
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
                            color = resource.palette.color
                        )
                        MoodFace(
                            modifier = Modifier.size(96.dp),
                            mood = resource
                        )
                        PreviousNextButton(
                            enabled = state.hasNext,
                            isPrevious = false,
                            onClick = { state.onEvent(MoodEvent.OnNext) },
                            color = resource.palette.color
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
                        state.statsRecords.foldComposable(
                            onLoading = { CircularLoading(Modifier.fillMaxWidth().height(250.dp)) },
                            onSuccess = { statsRecords: List<StatsRecord> ->
                                MoodBarStatistic(
                                    statsRecords = statsRecords.reversed(),
                                    currentStatsRecord = state.statsRecord,
                                    onClick = { state.onEvent(MoodEvent.OnSetMood(it)) }
                                )
                            }
                        )
                        VerticalSpacer(20.dp)
                    }
                }
            }
        )
    }
}