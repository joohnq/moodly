package com.joohnq.sleep_quality.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition


@Composable
fun SleepHistoryDay(
    record: SleepQualityRecordResource?,
    day: CalendarDay,
    onClick: () -> Unit,
) {
    val isSelected = record != null
    val color = when {
        day.position == DayPosition.MonthDate && !isSelected -> Colors.Brown80
        day.position == DayPosition.MonthDate && isSelected -> Colors.White
        else -> Colors.Brown30
    }

    val background = when {
        day.position == DayPosition.MonthDate && !isSelected -> Colors.White
        day.position == DayPosition.MonthDate && isSelected -> record.sleepQuality.palette.color
        else -> Colors.Brown10
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 2.dp)
            .aspectRatio(1f)
            .fillMaxSize()
            .clip(Dimens.Shape.Circle)
            .background(color = background)
            .clickable(enabled = isSelected) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            style = TextStyles.TextMdBold(),
            color = color
        )
    }
}