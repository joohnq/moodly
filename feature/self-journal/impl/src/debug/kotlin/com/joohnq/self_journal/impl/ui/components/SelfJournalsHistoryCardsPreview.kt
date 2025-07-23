package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.mapper.toGroupedByDate
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource

@Composable
fun SelfJournalsHistoryCardsPreview() {
    SelfJournalsHistoryCards(
        records = SelfJournalRecordResource.allSelfJournalRecordResourcePreview.toGroupedByDate(),
    )
}