package com.joohnq.moodapp.ui.presentation.journaling

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.edit_journaling_screen.EditJournalingScreen
import com.joohnq.moodapp.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.moodapp.ui.presentation.journaling.state.JournalingState
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel

class JournalingScreen : CustomScreen<JournalingState>() {
    @Composable
    override fun Screen(): JournalingState {
        val healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
        val journal by healthJournalViewModel.healthJournalState.collectAsState()

        fun onEvent(event: JournalingEvent) =
            when (event) {
                is JournalingEvent.OnNavigateToEditJournalingScreen ->
                    onNavigate(EditJournalingScreen(event.id), false)
            }

        return JournalingState(
            padding = PaddingValues(),
            journals = journal.healthJournalRecords.getValue(),
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: JournalingState) = JournalingUI(state)
}
