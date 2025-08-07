package com.joohnq.self_journal.history.component

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.parameter.SelfJournalRecordResourceParameterProvider
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.presentation.components.SelfJournalHistoryCard
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(SelfJournalRecordResourceParameterProvider::class)
    item: SelfJournalRecordResource,
) {
    SelfJournalHistoryCard(
        item = item
    )
}
