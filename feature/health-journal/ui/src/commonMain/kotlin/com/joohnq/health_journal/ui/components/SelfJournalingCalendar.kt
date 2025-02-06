package com.joohnq.health_journal.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.CalculateHealthJournalsAverageUseCase
import com.joohnq.mood.ui.mapper.toResource
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import org.koin.compose.koinInject

@Composable
fun SelfJournalingCalendar(
    calendarState: CalendarState = rememberCalendarState(),
    records: List<HealthJournalRecord>,
) {
    HorizontalCalendar(
        modifier = Modifier.fillMaxWidth(),
        state = calendarState,
        dayContent = { day ->
            val recordsInDay = records.filter { it.createdAt.date == day.date }
            val useCase: CalculateHealthJournalsAverageUseCase = koinInject()
            val average = useCase.invoke(recordsInDay).toResource()

            SelfJournalingDay(
                average = average,
                day = day,
            )
        },
        monthFooter = {
            SelfJournalingCalendarFooter()
        },
        monthHeader = {
            SelfJournalingMonthHeader()
        }
    )
}
