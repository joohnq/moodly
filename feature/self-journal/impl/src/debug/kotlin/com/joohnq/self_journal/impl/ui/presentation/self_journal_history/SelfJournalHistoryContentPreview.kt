package com.joohnq.self_journal.impl.ui.presentation.self_journal_history

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.parameter.ListSelfJournalRecordResourceParameterProvider
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.ui.entity.UiState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun SelfJournalHistoryContentPreview(
    @PreviewParameter(ListSelfJournalRecordResourceParameterProvider::class)
    list: List<SelfJournalRecordResource>
) {
    SelfJournalHistoryContent(
        state =
            SelfJournalHistoryContract.State(
                openDeleteDialog = false
            ),
        records =
            UiState.Success(
                list
            )
    )
}

@Preview
@Composable
fun SelfJournalHistoryContentPreview() {
    SelfJournalHistoryContent(
        state =
            SelfJournalHistoryContract.State(
                openDeleteDialog = true
            ),
        records =
            UiState.Success(
                listOf()
            )
    )
}
