package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun JournalInsightPreview() {
    JournalInsight(
        records = SelfJournalRecordResource.allSelfJournalRecordResourcePreview
    )
}