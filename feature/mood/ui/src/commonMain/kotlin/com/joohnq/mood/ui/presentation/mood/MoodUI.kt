package com.joohnq.mood.ui.presentation.mood

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.ui.components.MoodBarStatistic
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.ui.presentation.mood.event.toMoodEvent
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.CircularLoading
import com.joohnq.shared_resources.components.PreviousNextButton
import com.joohnq.shared_resources.components.SharedPanelComponent
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.your_mood_is
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoodPanel(
    resource: MoodResource,
    hasPrevious: Boolean,
    hasNext: Boolean,
    onEvent: (MoodEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .paddingHorizontalMedium()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(Res.string.your_mood_is),
            style = TextStyles.TextXlBold(),
            color = resource.palette.backgroundColor
        )
        Text(
            text = stringResource(resource.text),
            style = TextStyles.Heading2xlExtraBold(),
            color = resource.palette.backgroundColor
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
                color = resource.palette.color
            )
            MoodFace(
                modifier = Modifier.size(96.dp),
                resource = resource,
                backgroundColor = resource.palette.backgroundColor,
                color = resource.palette.color
            )
            PreviousNextButton(
                enabled = hasNext,
                isPrevious = false,
                onClick = { onEvent(MoodEvent.OnNext) },
                color = resource.palette.color
            )
        }
    }
}

@Composable
fun MoodContent(statsRecord: StatsRecord, statsRecords: UiState<List<StatsRecord>>, onEvent: (MoodEvent) -> Unit) {
    Column {
        Text(
            text = statsRecord.description,
            style = TextStyles.TextMdSemiBold(),
            color = Colors.Brown100Alpha64,
            modifier = Modifier.fillMaxWidth().paddingHorizontalMedium()
        )
        VerticalSpacer(40.dp)
        statsRecords.foldComposable(
            onLoading = { CircularLoading(Modifier.fillMaxWidth().height(250.dp)) },
            onSuccess = { statsRecords: List<StatsRecord> ->
                MoodBarStatistic(
                    statsRecords = statsRecords.reversed(),
                    currentStatsRecord = statsRecord,
                    onClick = { onEvent(MoodEvent.OnSetMood(it)) }
                )
            }
        )
        VerticalSpacer(20.dp)
    }
}

@Composable
fun MoodUI(
    statsRecord: StatsRecord?,
    statsRecords: UiState<List<StatsRecord>>,
    hasNext: Boolean,
    hasPrevious: Boolean,
    onEvent: (MoodEvent) -> Unit = {},
) {
    statsRecord?.run {
        val resource = mood.toResource()
        SharedPanelComponent(
            isDark = false,
            paddingValues = Dimens.Padding.HorizontalMedium,
            backgroundColor = resource.palette.color,
            image = Drawables.Images.MoodBackground,
            title = Res.string.mood,
            color = resource.palette.imageColor,
            onEvent = { event ->
                onEvent(event.toMoodEvent())
            },
            panel = {
                MoodPanel(
                    resource = resource,
                    hasPrevious = hasPrevious,
                    hasNext = hasNext,
                    onEvent = onEvent
                )
            },
            content = {
                MoodContent(
                    statsRecord = statsRecord,
                    statsRecords = statsRecords,
                    onEvent = onEvent
                )
            }
        )
    }
}


@Preview
@Composable
fun MoodUIPreview() {
    MoodUI(
        statsRecord = StatsRecord(),
        statsRecords = UiState.Success(listOf()),
        hasNext = true,
        hasPrevious = true,
        onEvent = {},
    )
}
