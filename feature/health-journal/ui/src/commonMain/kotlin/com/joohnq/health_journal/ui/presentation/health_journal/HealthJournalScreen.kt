package com.joohnq.health_journal.ui.presentation.health_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.health_journal.ui.presentation.health_journal.event.HealthJournalEvent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import kotlinx.datetime.LocalDate

@Composable
fun HealthJournalScreen(
    onNavigateAddHealthJournal: () -> Unit,
    onNavigateAllJournals: (LocalDate) -> Unit,
    onGoBack: () -> Unit,
) {
    val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
    val state by healthJournalViewModel.state.collectAsState()

    fun onEvent(event: HealthJournalEvent) =
        when (event) {
            HealthJournalEvent.OnGoBack -> onGoBack()
            HealthJournalEvent.OnNavigateToAddHealthJournal -> onNavigateAddHealthJournal()
            is HealthJournalEvent.OnClick -> onNavigateAllJournals(event.localDate)
        }

    return HealthJournalUI(
        state = state,
        onEvent = ::onEvent
    )
}
