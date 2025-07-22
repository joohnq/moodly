package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.fake.depressedMoodRecordResourcePreview
import com.joohnq.shared_resources.theme.Colors
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodDayPreview() {
    MoodDay(
        record = depressedMoodRecordResourcePreview,
        containerColor = Colors.White,
        day = CalendarDay(
            date = LocalDate(2023, 1, 1),
            position = DayPosition.MonthDate
        )
    )
}
