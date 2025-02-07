package com.joohnq.self_journal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.resource.MoodAverageResource
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition

@Composable
fun SelfJournalingDay(
    average: MoodAverageResource,
    day: CalendarDay,
) {
    val isSelected = average !is MoodAverageResource.Skipped
    val isInCurrentMonth = day.position == DayPosition.MonthDate

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 11.dp, vertical = 3.dp)
            .aspectRatio(1f)
            .fillMaxSize()
            .clip(Dimens.Shape.Circle)
            .background(
                color = if (!isSelected || isInCurrentMonth) Colors.White else average.backgroundColor,
                shape = Dimens.Shape.Circle
            )
            .border(
                width = 1.dp,
                color = if (isInCurrentMonth) Colors.Gray30 else Colors.Gray10,
                shape = Dimens.Shape.Circle
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected)
            MoodFace(
                modifier = Modifier.fillMaxSize(),
                average = average,
            )
    }
}
