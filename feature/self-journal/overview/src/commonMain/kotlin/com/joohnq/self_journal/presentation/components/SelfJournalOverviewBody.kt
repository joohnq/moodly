package com.joohnq.self_journal.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.self_journal.presentation.SelfJournalOverviewContract
import com.joohnq.shared_resources.Res
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
    SelfJournalOverviewCalendar(
        modifier = modifier,
        itemsInYear = state.itemsInYear,
        items = state.items,
        subtitle = stringResource(Res.string.journals_written_this_month),
        onCreate = {
            onEvent(SelfJournalOverviewContract.Event.NavigateToAddSelfJournal)
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
