package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.api.getNow
import com.joohnq.mood.impl.ui.resource.MoodAverageResource
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SelfJournalingDaySkippedPreview() {
    SelfJournalingDay(
        average = MoodAverageResource.Skipped,
        day =
            CalendarDay(
                date = getNow().date,
                position = DayPosition.InDate
            )
    )
}

@Preview
@Composable
fun SelfJournalingDayNeutralPreview() {
    SelfJournalingDay(
        average = MoodAverageResource.Neutral,
        day =
            CalendarDay(
                date = getNow().date,
                position = DayPosition.InDate
            )
    )
}

@Preview
@Composable
fun SelfJournalingDayNegativePreview() {
    SelfJournalingDay(
        average = MoodAverageResource.Negative,
        day =
            CalendarDay(
                date = getNow().date,
                position = DayPosition.InDate
            )
    )
}

@Preview
@Composable
fun SelfJournalingDayPositivePreview() {
    SelfJournalingDay(
        average = MoodAverageResource.Positive,
        day =
            CalendarDay(
                date = getNow().date,
                position = DayPosition.InDate
            )
    )
}
