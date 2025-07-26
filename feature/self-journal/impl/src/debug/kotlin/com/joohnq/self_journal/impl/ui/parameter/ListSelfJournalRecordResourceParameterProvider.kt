package com.joohnq.self_journal.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource

class ListSelfJournalRecordResourceParameterProvider : PreviewParameterProvider<List<SelfJournalRecordResource>> {
    override val values =
        sequenceOf(
            SelfJournalRecordResource.allSelfJournalRecordResourcePreview,
            emptyList()
        )
}
