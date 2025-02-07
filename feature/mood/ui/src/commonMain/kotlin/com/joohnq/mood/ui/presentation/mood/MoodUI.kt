package com.joohnq.mood.ui.presentation.mood

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.mapper.getTodayStatRecord
import com.joohnq.mood.ui.components.MoodContent
import com.joohnq.mood.ui.components.MoodPanel
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoodUI(
    records: UiState<List<MoodRecord>>,
    onEvent: (MoodEvent) -> Unit = {},
) {
    records.foldComposable(
        onSuccess = { records ->
            val record = records.getTodayStatRecord()
            val resource = record?.mood?.toResource()
            val hasToday = resource != null

            DecoratedConvexPanelList(
                containerColor = Colors.White,
                panelBackgroundColor = if (hasToday) resource.palette.color else Colors.Brown10,
                isDark = !hasToday,
                image = Drawables.Images.MoodBackground,
                color = if (hasToday) resource.palette.imageColor else Colors.Brown10,
                onAddButton = { onEvent(MoodEvent.OnAddStatScreen) },
                onGoBack = { onEvent(MoodEvent.OnGoBack) },
                panel = { modifier ->
                    MoodPanel(
                        modifier = modifier,
                        record = record,
                        resource = resource,
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
        records = UiState.Success(
            listOf(
                MoodRecord(
                    createdAt = LocalDateTime(2025, 1, 1, 0, 0, 0)
                )
            )
        ),
        onEvent = {},
    )
}

@Preview
@Composable
fun MoodUIPreviewDepressed() {
    MoodUI(
        records = UiState.Success(
            listOf(
                MoodRecord(
                    mood = Mood.Depressed,
                    description = "Description"
                ),
            )
        ),
        onEvent = {},
    )
}

@Preview
@Composable
fun MoodUIPreviewNeutral() {
    MoodUI(
        records = UiState.Success(
            listOf(
                MoodRecord(
                    mood = Mood.Neutral,
                    description = "Description"
                ),
            )
        ),
        onEvent = {},
    )
}

@Preview
@Composable
fun MoodUIPreviewHappy() {
    MoodUI(
        records = UiState.Success(
            listOf(
                MoodRecord(
                    mood = Mood.Happy,
                    description = "Description"
                ),
            )
        ),
        onEvent = {},
    )
}

@Preview
@Composable
fun MoodUIPreviewOverjoyed() {
    MoodUI(
        records = UiState.Success(
            listOf(
                MoodRecord(
                    mood = Mood.Overjoyed,
                    description = "Description"
                ),
            )
        ),
        onEvent = {},
    )
}
