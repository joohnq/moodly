package com.joohnq.self_journal.impl.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.self_journal.api.entity.SelfJournalRecord

class SelfJournalRecordParameterProvider : PreviewParameterProvider<SelfJournalRecord> {
    override val values =
        sequenceOf(
            SelfJournalRecord(),
            SelfJournalRecord.selfJournalDepressedPreview,
            SelfJournalRecord.selfJournalSadPreview,
            SelfJournalRecord.selfJournalNeutralPreview,
            SelfJournalRecord.selfJournalHappyPreview,
            SelfJournalRecord.selfJournalOverjoyedPreview
        )
}