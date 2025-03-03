package com.joohnq.mood.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.mood.ui.viewmodel.MoodIntent
import com.joohnq.shared_resources.theme.Colors

@Composable
fun MoodContent(
    modifier: Modifier = Modifier,
    record: MoodRecordResource?,
    records: List<MoodRecordResource>,
    onAction: (MoodIntent) -> Unit,
    onEvent: (MoodEvent) -> Unit
) {
    val containerColor = Colors.Gray5
    DescriptionSection(
        modifier = modifier,
        record = record
    )
    MoodInsight(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        onCreate = { onEvent(MoodEvent.OnAddMood) }
    )
    MoodCalendar(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        onCreate = { onEvent(MoodEvent.OnAddMood) }
    )
    MoodHistory(
        modifier = modifier,
        containerColor = containerColor,
        records = records.take(7),
        onSeeMore = {
            onEvent(MoodEvent.OnNavigateToMoodHistory)
        },
        onCreate = {
            onEvent(MoodEvent.OnAddMood)
        },
        onDelete = { id ->
            onAction(MoodIntent.Delete(id))
        }
    )
}
