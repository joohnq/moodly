package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.home.impl.ui.components.SelfJournalMetric
import com.joohnq.self_journal.impl.parameter.ListSelfJournalRecordResourceParameterProvider
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListSelfJournalRecordResourceParameterProvider::class)
    list: List<SelfJournalRecordResource>,
) {
    SelfJournalMetric(
        records = list
    )
}
