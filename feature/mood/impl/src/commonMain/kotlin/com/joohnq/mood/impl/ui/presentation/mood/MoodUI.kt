package com.joohnq.mood.impl.ui.presentation.mood

import androidx.compose.runtime.Composable
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.foldComposable
import com.joohnq.mood.ui.components.MoodContent
import com.joohnq.mood.ui.components.MoodPanel
import com.joohnq.mood.ui.mapper.getTodayMoodRecord
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.mood.ui.viewmodel.MoodIntent
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoodUI(
    records: UiState<List<MoodRecordResource>>,
    onAction: (MoodIntent) -> Unit = {},
    onEvent: (MoodEvent) -> Unit = {},
) {
    records.foldComposable(
        onSuccess = { records ->
            val record = records.getTodayMoodRecord()
            val hasToday = record != null

            DecoratedConvexPanelList(
                containerColor = Colors.White,
                panelBackgroundColor = if (hasToday) record.mood.palette.color else Colors.Brown10,
                isDark = !hasToday,
                image = Drawables.Images.MoodBackground,
                color = if (hasToday) record.mood.palette.imageColor else Colors.Brown10,
                onAddButton = { onEvent(MoodEvent.OnAddMood) },
                onGoBack = { onEvent(MoodEvent.OnGoBack) },
                panel = { modifier ->
                    MoodPanel(
                        modifier = modifier,
                        record = record,
                    )
                },
                content = { modifier ->
                    MoodContent(
                        modifier = modifier,
                        record = record,
                        records = records,
                        onEvent = onEvent,
                        onAction = onAction
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
                MoodRecordResource(
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
                MoodRecordResource(
                    mood = MoodResource.Depressed,
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
                MoodRecordResource(
                    mood = MoodResource.Neutral,
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
                MoodRecordResource(
                    mood = MoodResource.Happy,
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
                MoodRecordResource(
                    mood = MoodResource.Overjoyed,
                    description = "Description"
                ),
            )
        ),
        onEvent = {},
    )
}
