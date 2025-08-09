package com.joohnq.overview.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.add.ui.components.MoodBarStatistic
import com.joohnq.mood.add.ui.components.MoodHistoryContent
import com.joohnq.overview.presentation.MoodOverviewContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.mood_history
import com.joohnq.shared_resources.mood_statistics

@Composable
fun MoodOverviewContentBody(
    modifier: Modifier = Modifier,
    state: MoodOverviewContract.State,
    onEvent: (MoodOverviewContract.Event) -> Unit = {},
    onIntent: (MoodOverviewContract.Intent) -> Unit = {},
) {
    state.todayMood?.let {
        MoodOverviewDescription(
            modifier = modifier,
            description = it.description
        )
    }
    MoodOverviewInsight(
        modifier = modifier,
        isEmpty = state.items.isEmpty(),
        streakDays = state.streakDays,
        onCreate = { onEvent(MoodOverviewContract.Event.NavigateToAddMood) }
    )
    MoodOverviewCalendar(
        modifier = modifier,
        items = state.items,
        onCreate = { onEvent(MoodOverviewContract.Event.NavigateToAddMood) }
    )
    MoodHistoryContent(
        modifier = modifier,
        items = state.historyItems,
        onSeeMore = {
            onEvent(MoodOverviewContract.Event.NavigateToMoodHistory)
        },
        onCreate = {
            onEvent(MoodOverviewContract.Event.NavigateToAddMood)
        },
        onDelete = { id ->
            onIntent(MoodOverviewContract.Intent.Delete(id))
        }
    )
    SectionHeader(
        modifier = modifier,
        title = Res.string.mood_statistics,
    )
    MoodBarStatistic(
        items = state.items,
        height = 200.dp
    )
}