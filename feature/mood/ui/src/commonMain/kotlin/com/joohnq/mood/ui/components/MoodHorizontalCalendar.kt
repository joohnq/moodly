package com.joohnq.mood.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState

@Composable
fun MoodHorizontalCalendar(
    calendarState: CalendarState = rememberCalendarState(),
    containerColor: Color,
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
                containerColor = containerColor,
                day = day,
            )
        },
        monthHeader = {
            MoodMonthHeader()
        }
    )
}
