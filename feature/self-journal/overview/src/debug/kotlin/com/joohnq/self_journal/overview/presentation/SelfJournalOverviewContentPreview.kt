package com.joohnq.self_journal.overview.presentation

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.parameter.ListSelfJournalRecordResourceParameterProvider
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.presentation.SelfJournalOverviewContent
import com.joohnq.self_journal.presentation.SelfJournalOverviewContract
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListSelfJournalRecordResourceParameterProvider::class)
    list: List<SelfJournalRecordResource>,
) {
    SelfJournalOverviewContent(
        state =
            SelfJournalOverviewContract.State(
                items = list
            )
    )
}
