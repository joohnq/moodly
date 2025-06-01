package com.joohnq.self_journal.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.ui.presentation.self_journal.viewmodel.SelfJournalContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.journal_calendar
import com.joohnq.shared_resources.journal_history
import com.joohnq.shared_resources.journal_insight
import com.joohnq.shared_resources.journals_written_this_month
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalContent(
    modifier: Modifier = Modifier,
    records: List<SelfJournalRecordResource>,
    onEvent: (SelfJournalContract.Event) -> Unit = {},
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
        onCreate = {
            onEvent(SelfJournalContract.Event.NavigateToAddSelfJournal)
        },
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
        containerColor = containerColor,
        records = records.take(7),
        onClick = { onEvent(SelfJournalContract.Event.EditSelfJournal(it)) },
        onCreate = { onEvent(SelfJournalContract.Event.NavigateToAddSelfJournal) }
    )
    VerticalSpacer(10.dp)
}