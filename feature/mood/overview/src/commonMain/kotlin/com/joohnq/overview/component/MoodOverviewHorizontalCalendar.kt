package com.joohnq.overview.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState

@Composable
fun MoodOverviewHorizontalCalendar(
    calendarState: CalendarState = rememberCalendarState(),
    items: List<MoodRecordResource>,
) {
    HorizontalCalendar(
        modifier = Modifier.fillMaxWidth(),
        state = calendarState,
        dayContent = { day ->
            val itemInDay = items.filter { it.createdAt.date == day.date }
            val item = itemInDay.firstOrNull()

            MoodOverviewDay(
                item = item,
                day = day
            )
        },
        monthHeader = {
            MoodOverviewMonthHeader()
        }
    )
}