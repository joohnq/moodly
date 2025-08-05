package com.joohnq.self_journal.overview.component

import androidx.compose.runtime.Composable
import com.joohnq.api.getNow
import com.joohnq.mood.add.ui.resource.MoodAverageResource
import com.joohnq.mood.impl.ui.parameter.MoodAverageResourceParameterProvider
import com.joohnq.self_journal.presentation.components.SelfJournalOverviewContentDay
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(MoodAverageResourceParameterProvider::class) average: MoodAverageResource,
) {
    SelfJournalOverviewContentDay(
        average = average,
        day =
            CalendarDay(
                date = getNow().date,
                position = DayPosition.InDate
            )
    )
}
