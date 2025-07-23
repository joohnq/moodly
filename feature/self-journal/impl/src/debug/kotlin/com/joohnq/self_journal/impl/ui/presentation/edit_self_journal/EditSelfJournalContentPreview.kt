package com.joohnq.self_journal.impl.ui.presentation.edit_self_journal

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.impl.ui.presentation.edit_self_journal.viewmodel.EditSelfJournalState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun EditJournalingContentPreview() {
    EditJournalingContent(
        state = EditSelfJournalState(
            currentSelfJournalRecord = SelfJournalRecord.selfJournalDepressedPreview,
            editingSelfJournalRecord = SelfJournalRecord.selfJournalDepressedPreview,
            isEditing = false,
            openDeleteDialog = false
        ),
        canSave = false,
    )
}

@Preview
@Composable
fun EditJournalingContentIsEditingPreview() {
    EditJournalingContent(
        state = EditSelfJournalState(
            currentSelfJournalRecord = SelfJournalRecord.selfJournalDepressedPreview,
            editingSelfJournalRecord = SelfJournalRecord.selfJournalDepressedPreview,
            isEditing = true,
            openDeleteDialog = false
        ),
        canSave = false,
    )
}

@Preview
@Composable
fun EditJournalingContentCanSavePreview() {
    EditJournalingContent(
        state = EditSelfJournalState(
            currentSelfJournalRecord = SelfJournalRecord.selfJournalDepressedPreview,
            editingSelfJournalRecord = SelfJournalRecord.selfJournalDepressedPreview,
            isEditing = false,
            openDeleteDialog = false
        ),
        canSave = true,
    )
}

@Preview
@Composable
fun EditJournalingContentIsOpenDeleteDialogPreview() {
    EditJournalingContent(
        state = EditSelfJournalState(
            currentSelfJournalRecord = SelfJournalRecord.selfJournalDepressedPreview,
            editingSelfJournalRecord = SelfJournalRecord.selfJournalDepressedPreview,
            isEditing = false,
            openDeleteDialog = true
        ),
        canSave = false,
    )
}