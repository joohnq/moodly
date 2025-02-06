package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.capitalize
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.core.ui.mapper.toMonthCompleteDayAndYear
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.week_view
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.mapper.getTodaySleepQualityRecord
import com.joohnq.sleep_quality.ui.components.SleepPanel
import com.joohnq.sleep_quality.ui.components.SleepQualityWeekCalendar
import com.joohnq.sleep_quality.ui.components.SleepSection
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityState
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.Week
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SleepTopNavDate(
    modifier: Modifier = Modifier,
) {
    val now = getNow()

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = now.dayOfWeek.name.lowercase().capitalize(),
                style = TextStyles.TextXlBold(),
                color = Colors.Brown80
            )
            Text(
                text = now.date.toMonthCompleteDayAndYear(),
                style = TextStyles.TextMdRegular(),
                color = Colors.Gray60
            )
        }
    }
}


@Composable
fun SleepWeekView(
    modifier: Modifier = Modifier,
    week: Week,
    records: List<SleepQualityRecord>,
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.week_view,
        onSeeAll = {},
    )
    SleepQualityWeekCalendar(
        modifier = modifier,
        week = week,
        records = records
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SleepQualityUI(
    state: SleepQualityState,
    weekCalendarState: WeekCalendarState = rememberWeekCalendarState(),
    onEvent: (SleepQualityEvent) -> Unit = {},
) {
    state.sleepQualityRecords.foldComposable(
        onSuccess = { records ->
            val record = records.getTodaySleepQualityRecord()
            val resource = record?.sleepQuality?.toResource()
            val hasToday = resource != null

            DecoratedConvexPanelList(
                containerColor = Colors.White,
                isDark = !hasToday,
                image = Drawables.Images.SleepQualityBackground,
                color = if (hasToday) resource.palette.backgroundColor else Colors.Brown10,
                panelBackgroundColor = if (hasToday) resource.palette.color else Colors.Brown10,
                panel = { modifier ->
                    SleepPanel(
                        modifier = modifier,
                        record = record,
                        resource = resource,
                    )
                },
                onAddButton = { onEvent(SleepQualityEvent.OnAddSleepQuality) },
                onGoBack = { onEvent(SleepQualityEvent.OnGoBack) },
                content = { modifier ->
                    SleepWeekView(
                        modifier = modifier,
                        week = weekCalendarState.firstVisibleWeek,
                        records = records
                    )
                    VerticalSpacer(10.dp)
                    SleepSection(
                        modifier = modifier,
                        records = records.take(7),
                        onClick = {},
                    )
                }
            )
        }
    )
}

@Preview
@Composable
fun SleepQualityUIPreviewEmpty() {
    SleepQualityUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf(
                )
            )
        ),
    )
}

@Preview
@Composable
fun SleepQualityUIPreview() {
    SleepQualityUI(
        state = SleepQualityState(
            sleepQualityRecords = UiState.Success(
                listOf(
                    SleepQualityRecord()
                )
            )
        ),
    )
}