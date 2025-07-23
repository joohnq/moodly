package com.joohnq.self_journal.impl.ui.presentation.self_journal_history

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.presentation.self_journal_history.viewmodel.SelfJournalHistoryState
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SelfJournalHistoryContentPreview() {
    SelfJournalHistoryContent(
        state = SelfJournalHistoryState(),
        records = UiState.Success(
            SelfJournalRecordResource.allSelfJournalRecordResourcePreview
        ),
    )
}

@Preview
@Composable
fun SelfJournalHistoryContentIsOpenDeleteDialogPreview() {
    SelfJournalHistoryContent(
        state = SelfJournalHistoryState(
            openDeleteDialog = true
        ),
        records = UiState.Success(
            SelfJournalRecordResource.allSelfJournalRecordResourcePreview
        ),
    )
}