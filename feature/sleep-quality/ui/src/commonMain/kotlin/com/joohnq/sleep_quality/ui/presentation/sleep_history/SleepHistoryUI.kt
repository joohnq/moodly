package com.joohnq.sleep_quality.ui.presentation.sleep_history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.Title
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.remember.rememberWeekThreeChars
import com.joohnq.shared_resources.sleep_history
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.components.SleepHistoryLazyItems
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.joohnq.sleep_quality.ui.presentation.sleep_history.event.SleepHistoryEvent
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityState
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.YearMonth
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

fun YearMonth.toMonthAndYearCompleteString(): String =
    "${month.name} $year"

@Composable
fun Day(
    resource: SleepQualityResource?,
    day: CalendarDay,
    onClick: () -> Unit,
) {
    val isSelected = resource != null
    val color = when {
        day.position == DayPosition.MonthDate && !isSelected -> Colors.Brown80
        day.position == DayPosition.MonthDate && isSelected -> Colors.White
        else -> Colors.Brown30
    }

    val background = when {
        day.position == DayPosition.MonthDate && !isSelected -> Colors.White
        day.position == DayPosition.MonthDate && isSelected -> resource!!.palette.backgroundColor
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

@Composable
fun MonthHeader() {
    Row(modifier = Modifier.padding(bottom = 10.dp)) {
        val weekChars = rememberWeekThreeChars()
        weekChars.forEach { day ->
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(day),
                    style = TextStyles.TextSmSemiBold(),
                    color = Colors.Gray60
                )
            }
        }
    }
}

@Composable
fun SleepQualityCalendar(
    calendarState: CalendarState,
    records: List<SleepQualityRecord>,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onDayClick: (Int) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
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
                    painter = painterResource(Drawables.Icons.ArrowChevron),
                    contentDescription = null,
                    tint = Colors.Brown80,
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(
                onClick = onNextMonth,
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
                    painter = painterResource(Drawables.Icons.ArrowChevron),
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
        dayContent = { day ->
            val resource =
                records.find { it.createdAt == day.date }?.sleepQuality?.toResource()
            Day(
                resource = resource,
                day = day,
                onClick = { onDayClick(resource!!.id) }
            )
        },
        monthHeader = {
            MonthHeader()
        }
    )
}

@Composable
fun SleepHistoryUI(
    state: SleepQualityState,
    calendarState: CalendarState,
    onEvent: (SleepHistoryEvent) -> Unit,
) {
    state.sleepQualityRecords.foldComposable(
        onSuccess = { records ->
            Scaffold(
                containerColor = Colors.Brown10
            ) { padding ->
                Column(modifier = Modifier.padding(padding).paddingHorizontalMedium()) {
                    TopBar(
                        onGoBack = { onEvent(SleepHistoryEvent.OnGoBack) },
                    )
                    SleepQualityCalendar(
                        calendarState = calendarState,
                        records = records,
                        onPreviousMonth = { onEvent(SleepHistoryEvent.OnPreviousMonth) },
                        onNextMonth = { onEvent(SleepHistoryEvent.OnNextMonth) },
                        onDayClick = { id ->
                            onEvent(
                                SleepHistoryEvent.OnNavigateToSleepQuality(id)
                            )
                        }
                    )
                    Title(
                        modifier = Modifier.padding(vertical = 32.dp),
                        text = Res.string.sleep_history
                    )
                    SleepHistoryLazyItems(
                        records = records,
                        onEvent = onEvent
                    )
                }
            }
        }
    )
}