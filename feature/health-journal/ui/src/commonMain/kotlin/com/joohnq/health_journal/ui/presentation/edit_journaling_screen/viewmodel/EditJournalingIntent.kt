package com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel

import com.joohnq.health_journal.domain.entity.HealthJournalRecord

sealed class EditJournalingIntent {
    data object ResetState : EditJournalingIntent()
    data class GetEditJournaling(
        val id: Int,
        val healthJournalRecords: List<HealthJournalRecord>
    ) : EditJournalingIntent()

    data class UpdateTitle(val title: String) : EditJournalingIntent()
    data class UpdateDescription(val description: String) : EditJournalingIntent()
    data class UpdateIsEditing(val value: Boolean) : EditJournalingIntent()
    data class UpdateOpenDeleteDialog(val value: Boolean) : EditJournalingIntent()
}