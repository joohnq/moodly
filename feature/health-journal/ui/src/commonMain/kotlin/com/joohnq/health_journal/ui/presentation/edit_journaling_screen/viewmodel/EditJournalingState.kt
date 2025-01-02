package com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel

import com.joohnq.health_journal.domain.entity.HealthJournalRecord

data class EditJournalingState(
    val currentHealthJournalRecord: HealthJournalRecord = HealthJournalRecord(),
    val editingHealthJournalRecord: HealthJournalRecord = HealthJournalRecord(),
    val isEditing: Boolean = false,
    val openDeleteDialog: Boolean = false,
)