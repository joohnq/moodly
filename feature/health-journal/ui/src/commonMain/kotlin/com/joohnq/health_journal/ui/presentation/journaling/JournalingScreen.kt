package com.joohnq.health_journal.ui.presentation.journaling

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.health_journal.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.health_journal.ui.presentation.journaling.state.JournalingState
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import com.joohnq.shared_resources.CustomScreen
import com.joohnq.shared_resources.sharedViewModel

class JournalingScreen(
    private val onNavigateToEditJournaling: (Int) -> Unit,
    private val onNavigateToAllJournals: () -> Unit,
) : CustomScreen<JournalingState>() {
    @Composable
    override fun Screen(): JournalingState {
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val journal by healthJournalViewModel.state.collectAsState()

        fun onEvent(event: JournalingEvent) {
            when (event) {
                is JournalingEvent.OnNavigateToEditJournalingScreen -> onNavigateToEditJournaling(
                    event.id
                )

                JournalingEvent.OnNavigateToAllJournals -> onNavigateToAllJournals()
            }
        }

        return JournalingState(
            journals = journal.healthJournalRecords,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: JournalingState) = JournalingUI(state)
}
