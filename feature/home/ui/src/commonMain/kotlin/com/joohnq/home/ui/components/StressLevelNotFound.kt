package com.joohnq.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.CompositionLocalProviderPreview
import com.joohnq.core.ui.entity.CalendarInfo
import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toMonthNameString
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.use_case.CalculateHealthJournalsAverageUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.home.ui.presentation.home.getTodayHealthJournalRecord
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.mood.ui.resource.MoodAverageResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_journal
import com.joohnq.shared_resources.components.GiganticCreateCard
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.journals_written_in
import com.joohnq.shared_resources.lets_set_up_daily_journaling_and_self_reflection
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
    average: MoodAverageResource,
    day: CalendarDay,
) {
    val isSelected = average !is MoodAverageResource.Skipped
    val isInCurrentMonth = day.position == DayPosition.MonthDate

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 2.dp)
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
fun CalendarInfoCard(info: CalendarInfo) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(Dimens.Shape.Circle)
                .background(color = info.backgroundColor, shape = Dimens.Shape.Circle)
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
    FlowRow(
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterHorizontally)
    ) {
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
            val recordsInDay = records.filter { it.createdAt.date == day.date }
            val useCase: CalculateHealthJournalsAverageUseCase = koinInject()
            val average = useCase.invoke(recordsInDay).toResource()

            SelfJournalingDay(
                average = average,
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
                resource.createdAt.toMonthNameString()
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
            single<CalculateHealthJournalsAverageUseCase> { CalculateHealthJournalsAverageUseCase() }
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