package com.joohnq.self_journal.impl.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource

class SelfJournalRecordResourceParameterProvider : PreviewParameterProvider<SelfJournalRecordResource> {
    override val values =
        sequenceOf(
            SelfJournalRecordResource.selfJournalRecordResourceDepressedPreview,
            SelfJournalRecordResource.selfJournalRecordResourceSadPreview,
            SelfJournalRecordResource.selfJournalRecordResourceNeutralPreview,
            SelfJournalRecordResource.selfJournalRecordResourceHappyPreview,
            SelfJournalRecordResource.selfJournalRecordResourceOverjoyedPreview
        )
}