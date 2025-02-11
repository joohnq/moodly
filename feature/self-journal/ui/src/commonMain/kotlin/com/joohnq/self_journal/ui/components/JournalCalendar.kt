package com.joohnq.self_journal.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.self_journal.ui.mapper.getSelfJournalsInYear
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.components.GiganticCreateCard

@Composable
fun JournalCalendar(
    modifier: Modifier = Modifier,
    containerColor: Color,
    records: List<SelfJournalRecordResource>,
    subtitle: String,
    onCreate: () -> Unit,
) {
    val recordsInYear = records.getSelfJournalsInYear()

    GiganticCreateCard(
        modifier = modifier,
        containerColor = containerColor,
        title = recordsInYear,
        subtitle = subtitle,
        onCreate = onCreate,
        content = {
            SelfJournalingCalendar(
                records = records,
            )
        }
    )
}