package com.joohnq.self_journal.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.getSelfJournalsInYear
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.components.card.GiganticCreateCard

@Composable
fun SelfJournalOverviewCalendar(
    modifier: Modifier = Modifier,
    items: List<SelfJournalRecordResource>,
    subtitle: String,
    onCreate: () -> Unit = {},
) {
    val itemsInYear = items.getSelfJournalsInYear()

    GiganticCreateCard(
        modifier = modifier,
        title = itemsInYear,
        subtitle = subtitle,
        onCreate = onCreate,
        content = {
            SelfJournalOverviewCalendarContent(
                items = items
            )
        }
    )
}