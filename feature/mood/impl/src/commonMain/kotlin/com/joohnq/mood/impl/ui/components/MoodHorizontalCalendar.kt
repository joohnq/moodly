package com.joohnq.mood.impl.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.theme.Colors
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState

@Composable
fun MoodHorizontalCalendar(
    calendarState: CalendarState = rememberCalendarState(),
    records: List<MoodRecordResource>,
) {
    HorizontalCalendar(
        modifier = Modifier.fillMaxWidth(),
        state = calendarState,
        dayContent = { day ->
            val recordsInDay = records.filter { it.createdAt.date == day.date }
            val record = recordsInDay.firstOrNull()

            MoodDay(
                record = record,
                day = day,
            )
        },
        monthHeader = {
            MoodMonthHeader()
        }
    )
}
