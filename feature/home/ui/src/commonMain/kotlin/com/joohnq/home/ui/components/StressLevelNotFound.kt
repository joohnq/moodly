package com.joohnq.home.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.core.ui.CompositionLocalProviderPreview
import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.toMonthNameString
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.mapper.getTodayHealthJournalRecord
import com.joohnq.health_journal.domain.use_case.CalculateHealthJournalsAverageUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.health_journal.ui.components.JournalCalendar
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_journal
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.journals_written_in
import com.joohnq.shared_resources.lets_set_up_daily_journaling_and_self_reflection
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.dsl.module

@Composable
fun SelfJournalingMetric(
    records: List<HealthJournalRecord>,
    containerColor: Color = Colors.White,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val resource = records.getTodayHealthJournalRecord()

    if (resource == null)
        NotFoundHorizontal(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            title = Res.string.lets_set_up_daily_journaling_and_self_reflection,
            subtitle = Res.string.add_new_journal,
            image = Drawables.Images.SelfJournalingIllustration,
            onClick = onCreate
        )
    else {
        JournalCalendar(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            records = records,
            subtitle = stringResource(
                Res.string.journals_written_in,
                resource.createdAt.toMonthNameString()
            ),
            onCreate = onCreate,
            onClick = onClick,
        )
    }
}

@Preview
@Composable
fun SelfJournalingMetricPreviewToday() {
    CompositionLocalProviderPreview(
        module {
            single<GetHealthJournalsInYearUseCase> { GetHealthJournalsInYearUseCase() }
            single<CalculateHealthJournalsAverageUseCase> { CalculateHealthJournalsAverageUseCase() }
        }
    ) {
        SelfJournalingMetric(
            records = listOf(
                HealthJournalRecord(),
            ),
        )
    }
}

@Preview
@Composable
fun SelfJournalingMetricPreviewYesterday() {
    val now = getNow()

    SelfJournalingMetric(
        records = listOf(
            HealthJournalRecord(
                createdAt = LocalDateTime(now.year, now.month, now.date.dayOfMonth.minus(1), 0, 0)
            ),
        ),
    )
}