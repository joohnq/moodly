package com.joohnq.mood.impl.ui.presentation.overview

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.components.MoodContentPanel
import com.joohnq.mood.impl.ui.components.MoodOverviewContentBody
import com.joohnq.mood.impl.ui.mapper.MoodRecordResourceMapper.getTodayMoodRecord
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.UiStateMapper.foldComposable

@Composable
fun MoodOverviewContent(
    records: UiState<List<MoodRecordResource>>,
    onIntent: (MoodOverviewContract.Intent) -> Unit = {},
    onEvent: (MoodOverviewContract.Event) -> Unit = {},
) {
    records.foldComposable(
        onSuccess = { records ->
            val record = records.getTodayMoodRecord()
            val hasToday = record != null

            ConvexGroupLazyLayout(
                containerColor = Colors.White,
                panelBackgroundColor = if (hasToday) record.mood.palette.color else Colors.Brown10,
                isDark = !hasToday,
                image = Drawables.Images.MoodBackground,
                color = if (hasToday) record.mood.palette.imageColor else Colors.Brown10,
                onAddButton = { onEvent(MoodOverviewContract.Event.OnAddMood) },
                onGoBack = { onEvent(MoodOverviewContract.Event.OnGoBack) },
                panel = { modifier ->
                    MoodContentPanel(
                        modifier = modifier,
                        record = record
                    )
                },
                body = { modifier ->
                    MoodOverviewContentBody(
                        modifier = modifier,
                        record = record,
                        records = records,
                        onEvent = onEvent,
                        onAction = onIntent
                    )
                }
            )
        }
    )
}
