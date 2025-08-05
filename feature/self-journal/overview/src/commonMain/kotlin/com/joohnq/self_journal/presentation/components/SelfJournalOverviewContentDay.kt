package com.joohnq.self_journal.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.mood.add.ui.components.MoodFace
import com.joohnq.mood.add.ui.resource.MoodAverageResource
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition

@Composable
fun SelfJournalOverviewContentDay(
    average: MoodAverageResource,
    day: CalendarDay,
) {
    val isSelected = average !is MoodAverageResource.Skipped
    val isInCurrentMonth = day.position == DayPosition.MonthDate

    Box(
        modifier =
            Modifier
                .padding(horizontal = 11.dp, vertical = 3.dp)
                .sizeIn(maxWidth = 40.dp, maxHeight = 40.dp)
                .fillMaxSize()
                .aspectRatio(1f)
                .clip(Dimens.Shape.Circle)
                .border(
                    width = 1.dp,
                    color = if (isInCurrentMonth) Colors.Gray30 else Colors.Gray10,
                    shape = Dimens.Shape.Circle
                ),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            MoodFace(
                modifier = Modifier.fillMaxSize(),
                average = average
            )
        }
    }
}
