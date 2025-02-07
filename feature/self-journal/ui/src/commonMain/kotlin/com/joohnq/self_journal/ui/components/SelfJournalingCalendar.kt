package com.joohnq.self_journal.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.domain.use_case.CalculateSelfJournalsAverageUseCase
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import org.koin.compose.koinInject

@Composable
fun SelfJournalingCalendar(
    calendarState: CalendarState = rememberCalendarState(),
    records: List<SelfJournalRecord>,
) {
    HorizontalCalendar(
        modifier = Modifier.fillMaxWidth(),
        state = calendarState,
        dayContent = { day ->
            val recordsInDay = records.filter { it.createdAt.date == day.date }
            val useCase: CalculateSelfJournalsAverageUseCase = koinInject()
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
