package com.joohnq.mood.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition

@Composable
fun MoodDay(
    record: MoodRecordResource?,
    containerColor: Color,
    day: CalendarDay,
) {
    val isSelected = record != null
    val isInCurrentMonth = day.position == DayPosition.MonthDate

    val border =
        when {
            isInCurrentMonth -> Modifier.border(
                width = 1.dp,
                color = if (isInCurrentMonth) Colors.Gray30 else Colors.Gray10,
                shape = Dimens.Shape.Circle
            )

            else -> Modifier
        }

    val background =
        when {
            isInCurrentMonth && isSelected -> Modifier.background(
                color = record.mood.palette.color,
                shape = Dimens.Shape.Circle
            )

            else -> Modifier.background(
                color = containerColor,
                shape = Dimens.Shape.Circle
            )
        }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 11.dp, vertical = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {

        if (isInCurrentMonth)
            Text(
                text = day.date.dayOfMonth.toString(),
                style = TextStyles.TextXsMedium(),
                color = Colors.Gray60
            )

        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize()
                .clip(Dimens.Shape.Circle)
                .then(background)
                .then(border),
            contentAlignment = Alignment.Center
        ) {
            if (isInCurrentMonth && isSelected)
                MoodFace(
                    modifier = Modifier.fillMaxSize(),
                    resource = record.mood,
                )
        }
    }
}
