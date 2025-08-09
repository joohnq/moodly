package com.joohnq.self_journal.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.card.GiganticCreateCard
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.journal_calendar

@Composable
fun SelfJournalOverviewCalendar(
    modifier: Modifier = Modifier,
    items: List<SelfJournalRecordResource>,
    itemsInYear: String,
    subtitle: String,
    onCreate: () -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_calendar
    )

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
