package com.joohnq.self_journal.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.presentation.SelfJournalOverviewContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.journal_calendar
import com.joohnq.shared_resources.journal_history
import com.joohnq.shared_resources.journal_insight
import com.joohnq.shared_resources.journals_written_this_month
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalOverviewContentBody(
    modifier: Modifier = Modifier,
    records: List<SelfJournalRecordResource>,
    onIntent: (SelfJournalOverviewContract.Intent) -> Unit = {},
    onEvent: (SelfJournalOverviewContract.Event) -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_insight
    )
    SelfJournalOverviewInsight(
        modifier = modifier,
        records = records
    )
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_calendar
    )
    SelfJournalOverviewCalendar(
        modifier = modifier,
        records = records,
        subtitle = stringResource(Res.string.journals_written_this_month),
        onCreate = {
            onEvent(SelfJournalOverviewContract.Event.NavigateToAddSelfJournal)
        }
    )
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_history,
        onSeeMore = {
            onEvent(SelfJournalOverviewContract.Event.NavigateToSelfJournalHistory)
        }
    )
    SelfJournalOverviewHistory(
        modifier = modifier,
        records = records.take(7),
        onClick = { onEvent(SelfJournalOverviewContract.Event.NavigateToEditSelfJournal(it)) },
        onCreate = { onEvent(SelfJournalOverviewContract.Event.NavigateToAddSelfJournal) },
        onDelete = { id -> onIntent(SelfJournalOverviewContract.Intent.Delete(id)) }
    )
    VerticalSpacer(10.dp)
}