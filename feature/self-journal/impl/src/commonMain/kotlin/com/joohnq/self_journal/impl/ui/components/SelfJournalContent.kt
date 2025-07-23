package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.self_journal.impl.ui.presentation.self_journal.event.SelfJournalEvent
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalContent(
    modifier: Modifier = Modifier,
    records: List<SelfJournalRecordResource>,
    onEvent: (SelfJournalEvent) -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_insight
    )
    JournalInsight(
        modifier = modifier,
        records = records
    )
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_calendar
    )
    JournalCalendar(
        modifier = modifier,
        records = records,
        subtitle = stringResource(Res.string.journals_written_this_month),
        onCreate = {
            onEvent(SelfJournalEvent.OnNavigateToAddSelfJournal)
        },
    )
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_history,
        onSeeMore = {
            onEvent(SelfJournalEvent.OnNavigateToSelfHistory)
        }
    )
    JournalHistory(
        modifier = modifier,
        records = records.take(7),
        onClick = {onEvent(SelfJournalEvent.OnEditSelfJournal(it))},
        onCreate = {onEvent(SelfJournalEvent.OnNavigateToAddSelfJournal)}
    )
    VerticalSpacer(10.dp)
}