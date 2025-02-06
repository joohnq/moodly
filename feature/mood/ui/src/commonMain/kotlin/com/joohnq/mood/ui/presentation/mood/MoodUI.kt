package com.joohnq.mood.ui.presentation.mood

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.mapper.getTodayStatRecord
import com.joohnq.mood.ui.components.MoodContent
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.not_available
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.your_mood_is
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.minus
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoodPanel(
    modifier: Modifier = Modifier,
    resource: MoodResource?,
    onEvent: (MoodEvent) -> Unit
) {
    val hasToday = resource != null
    val iconTint = if (hasToday) Colors.White else Colors.Brown80
    val textColor = if (hasToday) Colors.White else Colors.Brown80

    Column(
        modifier = modifier
            .fillMaxWidth()
            .paddingHorizontalMedium(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!hasToday)
            Icon(
                painter = painterResource(Drawables.Icons.Outlined.MoodNeutral),
                contentDescription = stringResource(Res.string.mood),
                modifier = Modifier.size(48.dp),
                tint = iconTint
            )
        if (!hasToday)
            VerticalSpacer(24.dp)
        Text(
            text = if (hasToday) stringResource(Res.string.your_mood_is) else stringResource(Res.string.not_available),
            style = TextStyles.TextXlBold(),
            color = textColor
        )
        if (!hasToday)
            VerticalSpacer(10.dp)
        if (hasToday)
            Text(
                text = stringResource(resource.text),
                style = TextStyles.HeadingXlExtraBold(),
                color = Colors.White
            )
        VerticalSpacer(10.dp)
        if (hasToday)
            MoodFace(
                modifier = Modifier.size(96.dp),
                resource = resource,
                backgroundColor = Colors.White,
                color = resource.palette.color
            )
    }
}

@Composable
fun MoodUI(
    statsRecords: UiState<List<StatsRecord>>,
    onEvent: (MoodEvent) -> Unit = {},
) {
    statsRecords.foldComposable(
        onSuccess = { records ->
            val record = records.getTodayStatRecord()
            val resource = record?.mood?.toResource()
            val hasToday = resource != null

            DecoratedConvexPanelList(
                containerColor = Colors.White,
                panelBackgroundColor = if (hasToday) resource.palette.color else Colors.Brown10,
                isDark = false,
                image = Drawables.Images.MoodBackground,
                color = if (hasToday) resource.palette.imageColor else Colors.Brown10,
                onAddButton = { onEvent(MoodEvent.OnAddStatScreen) },
                onGoBack = { onEvent(MoodEvent.OnGoBack) },
                panel = { modifier ->
                    MoodPanel(
                        modifier = modifier,
                        resource = resource,
                        onEvent = onEvent
                    )
                },
                content = { modifier ->
                    MoodContent(
                        modifier = modifier,
                        record = record,
                        records = records,
                    )
                }
            )
        }
    )
}

@Preview
@Composable
fun MoodUIPreviewEmpty() {
    MoodUI(
        statsRecords = UiState.Success(
            listOf(
                StatsRecord(
                    createdAt = LocalDateTime(2025, 1, 1, 0, 0, 0)
                )
            )
        ),
        onEvent = {},
    )
}

@Preview
@Composable
fun MoodUIPreview() {
    MoodUI(
        statsRecords = UiState.Success(
            listOf(
                StatsRecord(
                    mood = Mood.Depressed,
                    description = "Description"
                ),
                StatsRecord(
                    mood = Mood.Sad,
                    description = "Description"
                ),
                StatsRecord(
                    mood = Mood.Neutral,
                    description = "Description"
                ),
                StatsRecord(
                    mood = Mood.Happy,
                    description = "Description"
                ),
                StatsRecord(
                    mood = Mood.Overjoyed,
                    description = "Description"
                ),
            )
        ),
        onEvent = {},
    )
}
