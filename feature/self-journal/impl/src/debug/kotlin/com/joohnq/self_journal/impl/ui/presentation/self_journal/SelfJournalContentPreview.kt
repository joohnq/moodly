package com.joohnq.self_journal.impl.ui.presentation.self_journal

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.ui.components.SelfJournalContent
import com.joohnq.self_journal.impl.ui.parameter.ListSelfJournalRecordResourceParameterProvider
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun SelfJournalContentPreview(
    @PreviewParameter(ListSelfJournalRecordResourceParameterProvider::class)
    list: List<SelfJournalRecordResource>,
) {
    SelfJournalContent(
        records = list
    )
}
