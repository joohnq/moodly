package com.joohnq.self_journal.overview.component

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.presentation.components.SelfJournalOverviewPanel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    SelfJournalOverviewPanel(
        count = 1,
        records = SelfJournalRecordResource.allSelfJournalRecordResourcePreview
    )
}