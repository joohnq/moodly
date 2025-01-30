package com.joohnq.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.CompositionLocalProviderPreview
import com.joohnq.core.ui.entity.CalendarInfo
import com.joohnq.core.ui.getNow
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.home.ui.presentation.home.getTodayHealthJournalRecord
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.GiganticCreateCard
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.remember.rememberCalendarInfo
import com.joohnq.shared_resources.remember.rememberWeekChars
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.dsl.module


@Composable
fun SelfJournalingNotFound(modifier: Modifier = Modifier, onClick: () -> Unit) {
    NotFoundHorizontal(
        modifier = modifier,
        title = Res.string.lets_set_up_daily_journaling_and_self_reflection,
        subtitle = Res.string.add_new_journal,
        image = Drawables.Images.SelfJournalingIllustration,
        onClick = onClick
    )
}

@Composable
fun SelfJournalingDay(
    resource: MoodResource?,
    day: CalendarDay,
) {
    val isSelected = resource != null
    val background = when {
        resource != null -> null
        else -> Colors.White
    }
    val border = when {
        day.position == DayPosition.MonthDate && !isSelected -> Colors.Gray30
        day.position != DayPosition.MonthDate -> Colors.Brown10
        else -> null
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 2.dp)
            .aspectRatio(1f)
            .fillMaxSize()
            .clip(Dimens.Shape.Circle)
            .then(
                background?.let {
                    Modifier.background(
                        color = it,
                        shape = Dimens.Shape.Circle
                    )
                } ?: Modifier
            )
            .then(
                border?.let {
                    Modifier.border(
                        width = 1.dp,
                        color = it,
                        shape = Dimens.Shape.Circle
                    )
                } ?: Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        resource?.let {
            MoodFace(
                modifier = Modifier.fillMaxSize(),
                resource = it
            )
        }
    }
}

@Composable
fun SelfJournalingMonthHeader() {
    Row(modifier = Modifier.padding(bottom = 10.dp)) {
        val weekChars = rememberWeekChars()
        weekChars.forEach { day ->
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(day),
                    style = TextStyles.TextSmBold(),
                    color = Colors.Gray80
                )
            }
        }
    }
}

@Composable
fun CalendarInfoCard(info: CalendarInfo){
    Row {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(color = info.backgroundColor)
                .border(
                    width = 1.dp,
                    color = info.borderColor,
                    shape = Dimens.Shape.Circle
                )
        )
        Text(
            text = stringResource(info.text),
            style = TextStyles.TextXsMedium(),
            color = Colors.Gray60
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelfJournalingCalendarFooter() {
    val calendarInfos = rememberCalendarInfo()
    FlowRow {
        calendarInfos.forEach { info -> CalendarInfoCard(info = info) }
    }
}


@Composable
fun SelfJournalingCalendar(
    calendarState: CalendarState = rememberCalendarState(),
    records: List<HealthJournalRecord>,
) {
    HorizontalCalendar(
        modifier = Modifier.fillMaxWidth(),
        state = calendarState,
        dayContent = { day ->
            val resource =
                records.find { it.createdAt.date == day.date }?.mood?.toResource()

            SelfJournalingDay(
                resource = resource,
                day = day,
            )
        },
        monthFooter = {
            SelfJournalingCalendarFooter()
        },
        monthHeader = {
            SelfJournalingMonthHeader()
        }
    )
}

@Composable
fun SelfJournalingMetric(
    records: List<HealthJournalRecord>,
    onCreate: () -> Unit,
    onClick: () -> Unit,
) {
    val resource = records.getTodayHealthJournalRecord()
    val useCase: GetHealthJournalsInYearUseCase = koinInject()
    val recordsInYear = useCase.invoke(healthJournals = records)

    if (resource == null)
        SelfJournalingNotFound(modifier = Modifier.paddingHorizontalMedium(), onClick = onCreate)
    else {
        GiganticCreateCard(
            modifier = Modifier.paddingHorizontalMedium(),
            title = recordsInYear,
            subtitle = stringResource(
                Res.string.journals_written_in,
                resource.createdAt.month.name
            ),
            onCreate = onCreate,
            onClick = onClick,
            content = {
                SelfJournalingCalendar(
                    records = records,
                )
            }
        )
    }
}

@Preview
@Composable
fun SelfJournalingMetricPreview() {
    CompositionLocalProviderPreview(
        module {
            single<GetHealthJournalsInYearUseCase> { GetHealthJournalsInYearUseCase() }
        }
    ) {
        SelfJournalingMetric(
            records = listOf(
                HealthJournalRecord(
                    createdAt = getNow()
                ),
            ),
            onCreate = {},
            onClick = {}
        )
    }
}