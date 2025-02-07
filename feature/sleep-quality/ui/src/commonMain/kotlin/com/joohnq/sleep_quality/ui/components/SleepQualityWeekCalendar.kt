package com.joohnq.sleep_quality.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.Week
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SleepQualityWeekCalendar(
    modifier: Modifier = Modifier,
    week: Week,
    records: List<SleepQualityRecordResource>,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        week.days.forEach { day ->
            val resource =
                records.find { it.createdAt == day.date }
            SleepWeekDay(
                modifier = Modifier.weight(1f),
                resource = resource,
                day = day,
            )
        }
    }
}


@Preview
@Composable
fun SleepQualityWeekCalendarPreview() {
    SleepQualityWeekCalendar(
        records = listOf(
            SleepQualityRecordResource()
        ),
        week = rememberWeekCalendarState().firstVisibleWeek,
    )
}