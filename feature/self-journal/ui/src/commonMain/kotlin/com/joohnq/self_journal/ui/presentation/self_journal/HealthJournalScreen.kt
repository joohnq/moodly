package com.joohnq.self_journal.ui.presentation.self_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.self_journal.ui.presentation.self_journal.event.SelfJournalEvent
import com.joohnq.self_journal.ui.viewmodel.SelfJournalViewModel
import kotlinx.datetime.LocalDate

@Composable
fun SelfJournalScreen(
    onNavigateAddSelfJournal: () -> Unit,
    onNavigateAllJournals: (LocalDate) -> Unit,
    onGoBack: () -> Unit,
) {
    val selfJournalViewModel: SelfJournalViewModel = sharedViewModel()
    val state by selfJournalViewModel.state.collectAsState()

    fun onEvent(event: SelfJournalEvent) =
        when (event) {
            SelfJournalEvent.OnGoBack -> onGoBack()
            SelfJournalEvent.OnNavigateToAddSelfJournal -> onNavigateAddSelfJournal()
            is SelfJournalEvent.OnClick -> onNavigateAllJournals(event.localDate)
        }

    return SelfJournalUI(
        state = state,
        onEvent = ::onEvent
    )
}
