package com.joohnq.gratefulness.overview.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.api.getNow
import com.joohnq.api.mapper.LocalDateMapper.toDayOfWeekFullMonthAbbreviatedDayYear
import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.gratefulness_overview_other_day_title
import com.joohnq.shared_resources.gratefulness_overview_today_title
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource

@Composable
fun GratefulnessOverviewPanel(
    modifier: Modifier = Modifier,
    weekCalendarState: WeekCalendarState,
    items: List<Gratefulness>,
    selectedDate: LocalDate,
    iAmGratefulFor: String?,
    onSelectDate: (LocalDate) -> Unit,
) {
    val isToday = selectedDate == getNow().date

    Column {
        WeekCalendar(
            state = weekCalendarState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            dayContent = { day ->
                val hasItem = items.any { it.createdAt.date == day.date }

                GratefulnessOverviewPanelDay(
                    hasItem = hasItem,
                    selectedDate = selectedDate,
                    day = day,
                    onClick = { onSelectDate(day.date) }
                )
            }
        )

        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalSpacer(32.dp)
            Text(
                text = selectedDate.toDayOfWeekFullMonthAbbreviatedDayYear(),
                style = TextStyles.textSmMedium(),
                color = Colors.Gray60,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(16.dp)
            Text(
                text =
                    stringResource(
                        if (isToday) {
                            Res.string.gratefulness_overview_today_title
                        } else {
                            Res.string.gratefulness_overview_other_day_title
                        },
                        iAmGratefulFor ?: "..."
                    ),
                style = TextStyles.headingMdRegular(),
                color = Colors.Brown90,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(16.dp)
        }
    }
}
