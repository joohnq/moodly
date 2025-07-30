package com.joohnq.self_journal.impl.ui.presentation.history

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
    list: List<SelfJournalRecordResource>,
) {
    SelfJournalHistoryContent(
        state =
            SelfJournalHistoryContract.State(
                records =
                    UiState.Success(
                        list
                    )
            )
    )
}
