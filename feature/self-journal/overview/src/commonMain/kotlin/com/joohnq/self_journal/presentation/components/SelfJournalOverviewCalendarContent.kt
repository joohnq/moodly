package com.joohnq.self_journal.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.calculateSelfJournalsAverage
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState

@Composable
fun SelfJournalOverviewCalendarContent(
    calendarState: CalendarState = rememberCalendarState(),
    records: List<SelfJournalRecordResource>,
) {
    HorizontalCalendar(
        modifier = Modifier.fillMaxWidth(),
        state = calendarState,
        dayContent = { day ->
            val recordsInDay = records.filter { it.createdAt.date == day.date }
            val average = recordsInDay.calculateSelfJournalsAverage()

            SelfJournalOverviewContentDay(
                average = average,
                day = day
            )
        },
        monthFooter = {
            SelfJournalOverviewCalendarContentFooter()
        },
        monthHeader = {
            SelfJournalOverviewCalendarContentMonthHeader()
        }
    )
}
