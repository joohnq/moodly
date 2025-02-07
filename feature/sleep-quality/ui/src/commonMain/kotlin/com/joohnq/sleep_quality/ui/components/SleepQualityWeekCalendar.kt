package com.joohnq.sleep_quality.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.Week
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SleepQualityWeekCalendar(
    modifier: Modifier = Modifier,
    week: Week,
    records: List<SleepQualityRecord>,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        week.days.forEach { day ->
            val resource =
                records.find { it.createdAt == day.date }?.sleepQuality?.toResource()
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
            SleepQualityRecord()
        ),
        week = rememberWeekCalendarState().firstVisibleWeek,
    )
}