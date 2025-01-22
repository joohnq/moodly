package com.joohnq.health_journal.ui.presentation.journaling

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.health_journal.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel

@Composable
fun JournalingScreen(
    onNavigateToEditJournaling: (Int) -> Unit,
    onNavigateToAllJournals: () -> Unit,
) {
    val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
    val journal by healthJournalViewModel.state.collectAsState()

    fun onEvent(event: JournalingEvent) {
        when (event) {
            is JournalingEvent.OnNavigateToEditJournalingScreen ->
                onNavigateToEditJournaling(event.id)

            JournalingEvent.OnNavigateToAllJournals -> onNavigateToAllJournals()
        }
    }

    JournalingUI(
        journals = journal.healthJournalRecords,
        onEvent = ::onEvent
    )
}
