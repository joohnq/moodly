package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.parameter.ListSelfJournalRecordResourceParameterProvider
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun SelfJournalingCalendarPreview(
    @PreviewParameter(ListSelfJournalRecordResourceParameterProvider::class)
    items: List<SelfJournalRecordResource>
) {
    SelfJournalingCalendar(
        records = items
    )
}