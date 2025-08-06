package com.joohnq.self_journal.history.presentation

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.parameter.ListSelfJournalRecordResourceParameterProvider
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.toGroupedByDate
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.presentation.SelfJournalHistoryContent
import com.joohnq.self_journal.presentation.SelfJournalHistoryContract
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListSelfJournalRecordResourceParameterProvider::class)
    list: List<SelfJournalRecordResource>,
) {
    SelfJournalHistoryContent(
        state =
            SelfJournalHistoryContract.State(
                items = list.toGroupedByDate()
            )
    )
}
