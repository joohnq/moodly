package com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel

import com.joohnq.health_journal.domain.entity.HealthJournalRecord

data class EditJournalingState(
    val currentHealthJournalRecord: HealthJournalRecord = HealthJournalRecord.init(),
    val editingHealthJournalRecord: HealthJournalRecord = HealthJournalRecord.init(),
    val isEditing: Boolean = false,
    val openDeleteDialog: Boolean = false
)