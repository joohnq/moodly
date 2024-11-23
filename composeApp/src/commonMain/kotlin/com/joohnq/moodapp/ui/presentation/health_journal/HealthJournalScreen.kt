package com.joohnq.moodapp.ui.presentation.health_journal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.add_journaling_screen.AddJournalingScreen
import com.joohnq.moodapp.ui.presentation.health_journal.event.HealthJournalEvent
import com.joohnq.moodapp.ui.presentation.health_journal.state.HealthJournalState
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel

class HealthJournalScreen : CustomScreen<HealthJournalState>() {
    @Composable
    override fun Screen(): HealthJournalState {
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val healthJournalState by healthJournalViewModel.healthJournalState.collectAsState()

        fun onEvent(event: HealthJournalEvent) =
            when (event) {
                HealthJournalEvent.OnGoBack -> onGoBack()
                HealthJournalEvent.OnNavigateToAddHealthJournalScreen ->
                    onNavigate(AddJournalingScreen())
            }

        return HealthJournalState(
            healthJournal = healthJournalState.healthJournalRecords.getValue(),
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: HealthJournalState) = HealthJournalUI(state)
}
