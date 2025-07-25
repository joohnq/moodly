package com.joohnq.self_journal.impl.ui.presentation.edit_self_journal

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.impl.ui.parameter.SelfJournalRecordParameterProvider
import com.joohnq.shared_resources.components.parameter.BooleanParameterProvider
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun EditJournalingContentPreview(
    @PreviewParameter(SelfJournalRecordParameterProvider::class)
    item: SelfJournalRecord,
) {
    EditJournalingContent(
        state = EditSelfJournalContract.State(
            currentSelfJournalRecord = item,
            editingSelfJournalRecord = item,
            isEditing = false,
            openDeleteDialog = false
        ),
        canSave = false,
    )
}

@Preview
@Composable
fun EditJournalingContentPreview(
    @PreviewParameter(BooleanParameterProvider::class)
    boolean: Boolean,
) {
    EditJournalingContent(
        state = EditSelfJournalContract.State(
            isEditing = boolean,
            openDeleteDialog = !boolean
        ),
        canSave = boolean,
    )
}