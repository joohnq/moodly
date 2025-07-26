package com.joohnq.mood.impl.ui.presentation.mood

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.components.MoodContentBody
import com.joohnq.mood.impl.ui.components.MoodContentPanel
import com.joohnq.mood.impl.ui.mapper.getTodayMoodRecord
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.foldComposable

@Composable
fun MoodContent(
    records: UiState<List<MoodRecordResource>>,
    onAction: (MoodContract.Intent) -> Unit = {},
    onEvent: (MoodContract.Event) -> Unit = {},
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
                onAddButton = { onEvent(MoodContract.Event.OnAddMood) },
                onGoBack = { onEvent(MoodContract.Event.OnGoBack) },
                panel = { modifier ->
                    MoodContentPanel(
                        modifier = modifier,
                        record = record
                    )
                },
                body = { modifier ->
                    MoodContentBody(
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
