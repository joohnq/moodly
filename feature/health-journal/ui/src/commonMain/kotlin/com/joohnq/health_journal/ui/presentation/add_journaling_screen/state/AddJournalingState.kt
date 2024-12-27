package com.joohnq.health_journal.ui.presentation.add_journaling_screen.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.mood.domain.Mood
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.AddingJournalingIntent
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.event.AddJournalingEvent
import com.joohnq.mood.viewmodel.HealthJournalIntent

data class AddJournalingState(
    val snackBarState: SnackbarHostState,
    val selectedMood: Mood?,
    val title: String,
    val titleError: String?,
    val desc: String,
    val onAddingAction: (AddingJournalingIntent) -> Unit = {},
    val onAction: (HealthJournalIntent) -> Unit = {},
    val onEvent: (AddJournalingEvent) -> Unit = {},
)