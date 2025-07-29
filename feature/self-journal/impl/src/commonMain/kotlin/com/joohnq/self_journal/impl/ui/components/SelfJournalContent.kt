package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalContract
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.journal_calendar
import com.joohnq.shared_resources.journal_history
import com.joohnq.shared_resources.journal_insight
import com.joohnq.shared_resources.journals_written_this_month
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalContent(
    modifier: Modifier = Modifier,
    records: List<SelfJournalRecordResource>,
    onEvent: (SelfJournalContract.Event) -> Unit = {},
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
            onEvent(SelfJournalContract.Event.OnNavigateToAddSelfJournal)
        }
    )
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_history,
        onSeeMore = {
            onEvent(SelfJournalContract.Event.OnNavigateToSelfHistory)
        }
    )
    JournalHistory(
        modifier = modifier,
        records = records.take(7),
        onClick = { onEvent(SelfJournalContract.Event.OnEdit(it)) },
        onCreate = { onEvent(SelfJournalContract.Event.OnNavigateToAddSelfJournal) },
        onDelete = { id -> onEvent(SelfJournalContract.Event.OnDelete(id)) }
    )
    VerticalSpacer(10.dp)
}
