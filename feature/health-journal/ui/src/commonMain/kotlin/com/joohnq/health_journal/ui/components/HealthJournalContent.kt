package com.joohnq.health_journal.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource

@Composable
fun HealthJournalContent(
    modifier: Modifier = Modifier,
    records: List<HealthJournalRecord>,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
    onSeeAll: () -> Unit = {}
) {
    val containerColor = Colors.Gray5

    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_insight
    )
    JournalInsight(
        modifier = modifier,
        containerColor = containerColor,
        records = records
    )
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_calendar
    )
    JournalCalendar(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        subtitle = stringResource(Res.string.journals_written_this_month),
        onCreate = onCreate,
        onClick = onClick,
    )
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_history,
        onSeeAll = onSeeAll
    )
    JournalHistory(
        modifier = modifier,
        containerColor = containerColor,
        records = records.take(7),
    )
    VerticalSpacer(10.dp)
}