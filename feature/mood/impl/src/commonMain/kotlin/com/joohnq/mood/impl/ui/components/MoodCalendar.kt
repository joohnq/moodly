package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.mood.impl.ui.mapper.getMonthDaysRecordsString
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.GiganticCreateCard
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.mood_calendar
import com.joohnq.shared_resources.moods_logged_this_month
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoodCalendar(
    modifier: Modifier = Modifier,
    containerColor: Color = Colors.White,
    records: List<MoodRecordResource>,
    onCreate: () -> Unit = {},
) {
    val recordsInMonth = records.getMonthDaysRecordsString()

    SectionHeader(
        modifier = modifier,
        title = Res.string.mood_calendar
    )
    GiganticCreateCard(
        modifier = modifier,
        containerColor = containerColor,
        title = recordsInMonth,
        subtitle = stringResource(Res.string.moods_logged_this_month),
        onCreate = onCreate,
        content = {
            MoodHorizontalCalendar(
                containerColor = containerColor,
                records = records,
            )
        }
    )
}

@Composable
@Preview
fun MoodCalendarPreview() {
    MoodCalendar(
        records = listOf(
            MoodRecordResource(),
            MoodRecordResource()
        )
    )
}