package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.self_journal.impl.ui.mapper.SelfJournalRecordResourceMapper.getSelfJournalsInYear
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.components.card.GiganticCreateCard

@Composable
fun JournalCalendar(
    modifier: Modifier = Modifier,
    records: List<SelfJournalRecordResource>,
    subtitle: String,
    onCreate: () -> Unit = {},
) {
    val recordsInYear = records.getSelfJournalsInYear()

    GiganticCreateCard(
        modifier = modifier,
        title = recordsInYear,
        subtitle = subtitle,
        onCreate = onCreate,
        content = {
            SelfJournalingCalendar(
                records = records
            )
        }
    )
}
