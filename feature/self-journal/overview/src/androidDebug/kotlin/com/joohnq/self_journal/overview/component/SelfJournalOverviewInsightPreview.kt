package com.joohnq.self_journal.overview.component

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.impl.parameter.ListSelfJournalRecordResourceParameterProvider
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.getGrouped
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.self_journal.presentation.components.SelfJournalOverviewInsight
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(ListSelfJournalRecordResourceParameterProvider::class)
    list: List<SelfJournalRecordResource>,
) {
    SelfJournalOverviewInsight(
        items = list.getGrouped()
    )
}
