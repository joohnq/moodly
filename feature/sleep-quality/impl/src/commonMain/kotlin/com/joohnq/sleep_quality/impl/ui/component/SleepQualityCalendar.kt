package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.ui.mapper.toMonthAndYearCompleteString
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import org.jetbrains.compose.resources.painterResource

@Composable
fun SleepQualityCalendar(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    calendarState: CalendarState,
    records: List<SleepQualityRecordResource>,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onDayClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = calendarState.firstVisibleMonth.yearMonth.toMonthAndYearCompleteString(),
            style = TextStyles.HeadingMdExtraBold(),
            color = Colors.Brown80
        )
        Row {
            IconButton(
                onClick = onPreviousMonth,
                modifier = Modifier.size(40.dp),
                enabled = calendarState.canScrollBackward,
                colors = IconButtonColors(
                    containerColor = Colors.Transparent,
                    contentColor = Colors.Brown80,
                    disabledContainerColor = Colors.Transparent,
                    disabledContentColor = Colors.Brown40
                ),
            ) {
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.ArrowOpen),
                    contentDescription = null,
                    tint = Colors.Brown80,
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(
                onClick = onNextMonth,
                modifier = Modifier.size(40.dp),
                enabled = calendarState.canScrollForward,
                colors = IconButtonColors(
                    containerColor = Colors.Transparent,
                    contentColor = Colors.Brown80,
                    disabledContainerColor = Colors.Transparent,
                    disabledContentColor = Colors.Brown40
                ),
            ) {
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.ArrowOpen),
                    contentDescription = null,
                    tint = Colors.Brown80,
                    modifier = Modifier.size(24.dp).rotate(180f),
                )
            }
        }
    }
    VerticalSpacer(10.dp)
    HorizontalCalendar(
        modifier = Modifier.fillMaxWidth(),
        state = calendarState,
        contentPadding = padding,
        dayContent = { day ->
            val resource =
                records.find { it.createdAt == day.date }
            SleepHistoryDay(
                record = resource,
                day = day,
                onClick = { onDayClick() }
            )
        },
        monthHeader = {
            SleepHistoryMonthHeader()
        }
    )
}
