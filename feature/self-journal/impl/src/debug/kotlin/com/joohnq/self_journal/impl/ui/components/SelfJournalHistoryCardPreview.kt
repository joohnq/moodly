package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.parameter.SelfJournalRecordResourceParameterProvider
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun SelfJournalHistoryCardPreview(
    @PreviewParameter(SelfJournalRecordResourceParameterProvider::class)
    item: SelfJournalRecordResource
) {
    SelfJournalHistoryCard(
        record = item,
    )
}