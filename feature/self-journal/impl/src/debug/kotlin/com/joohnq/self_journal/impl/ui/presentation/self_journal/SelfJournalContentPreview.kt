package com.joohnq.self_journal.impl.ui.presentation.self_journal

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.components.SelfJournalContent
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SelfJournalContentPreview() {
    SelfJournalContent(
        records = SelfJournalRecordResource.allSelfJournalRecordResourcePreview,
    )
}