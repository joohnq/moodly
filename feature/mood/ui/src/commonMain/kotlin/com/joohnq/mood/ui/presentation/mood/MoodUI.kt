package com.joohnq.mood.ui.presentation.mood

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.UiState
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.ui.components.MoodContent
import com.joohnq.mood.ui.components.MoodPanel
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.DecoratedConvexPanelItem
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

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
        DecoratedConvexPanelItem(
            containerColor = Colors.White,
            panelBackgroundColor = resource.palette.color,
            title = Res.string.mood,
            isDark = false,
            image = Drawables.Images.MoodBackground,
            color = resource.palette.imageColor,
            onAddButton = { onEvent(MoodEvent.OnAddStatScreen) },
            onGoBack = { onEvent(MoodEvent.OnGoBack) },
            panel = { modifier ->
                MoodPanel(
                    modifier = modifier,
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
