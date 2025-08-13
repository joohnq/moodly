package com.joohnq.self_journal.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.self_journal.presentation.SelfJournalOverviewContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.card.GiganticCreateCard
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.journal_calendar
import com.joohnq.shared_resources.journals_written_this_month
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalOverviewBody(
    modifier: Modifier = Modifier,
    state: SelfJournalOverviewContract.State,
    onIntent: (SelfJournalOverviewContract.Intent) -> Unit = {},
    onEvent: (SelfJournalOverviewContract.Event) -> Unit = {},
) {
    SelfJournalOverviewInsight(
        modifier = modifier,
        items = state.groupedInsightItems,
        onCreate = {
            onEvent(SelfJournalOverviewContract.Event.NavigateToAddSelfJournal)
        }
    )
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_calendar
    )
    GiganticCreateCard(
        modifier = modifier,
        title = state.itemsInYear,
        subtitle = stringResource(Res.string.journals_written_this_month),
        onCreate = {
            onEvent(SelfJournalOverviewContract.Event.NavigateToAddSelfJournal)
        },
        content = {
            SelfJournalOverviewCalendarContent(
                items = state.items
            )
        }
    )
    SelfJournalOverviewHistory(
        modifier = modifier,
        items = state.historyItems,
        onClick = { onEvent(SelfJournalOverviewContract.Event.NavigateToEditSelfJournal(it)) },
        onCreate = { onEvent(SelfJournalOverviewContract.Event.NavigateToAddSelfJournal) },
        onDelete = { id -> onIntent(SelfJournalOverviewContract.Intent.Delete(id)) },
        onSeeMore = { onEvent(SelfJournalOverviewContract.Event.NavigateToSelfJournalHistory) }
    )
}
