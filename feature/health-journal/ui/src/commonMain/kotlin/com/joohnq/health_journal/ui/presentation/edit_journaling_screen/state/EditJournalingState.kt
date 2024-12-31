package com.joohnq.health_journal.ui.presentation.edit_journaling_screen.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.event.EditJournalingEvent
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel.EditJournalingIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent

data class EditJournalingState(
    val snackBarState: SnackbarHostState,
    val healthJournal: HealthJournalRecord,
    val isEditing: Boolean,
    val canSave: Boolean,
    val openDeleteDialog: Boolean,
    val onEvent: (EditJournalingEvent) -> Unit = {},
    val onEditingAction: (EditJournalingIntent) -> Unit = {},
    val onAction: (HealthJournalIntent) -> Unit = {}
)