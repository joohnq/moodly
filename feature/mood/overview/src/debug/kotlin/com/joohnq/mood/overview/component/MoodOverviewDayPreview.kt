package com.joohnq.mood.overview.component

import androidx.compose.runtime.Composable
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.parameter.MoodRecordResourceParameterProvider
import com.joohnq.overview.component.MoodOverviewDay
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(MoodRecordResourceParameterProvider::class)
    item: MoodRecordResource,
) {
    MoodOverviewDay(
        item = item,
        day =
            CalendarDay(
                date = LocalDate(2023, 1, 1),
                position = DayPosition.MonthDate
            )
    )
}
