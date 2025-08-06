package com.joohnq.overview.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.getMonthDaysRecordsString
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.card.GiganticCreateCard
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.mood_calendar
import com.joohnq.shared_resources.moods_logged_this_month
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodOverviewCalendar(
    modifier: Modifier = Modifier,
    items: List<MoodRecordResource>,
    onCreate: () -> Unit = {},
) {
    val itemsInMonth = items.getMonthDaysRecordsString()

    SectionHeader(
        modifier = modifier,
        title = Res.string.mood_calendar
    )
    GiganticCreateCard(
        modifier = modifier,
        title = itemsInMonth,
        subtitle = stringResource(Res.string.moods_logged_this_month),
        onCreate = onCreate,
        content = {
            MoodOverviewHorizontalCalendar(
                items = items
            )
        }
    )
}