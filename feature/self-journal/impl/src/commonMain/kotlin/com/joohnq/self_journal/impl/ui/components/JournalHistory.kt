package com.joohnq.self_journal.impl.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.NotFoundVertical
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.write_journal
import com.joohnq.shared_resources.write_your_first_journal_to_see_journal_history_lets_do_it_now_for_better_mental_health

@Composable
fun JournalHistory(
    modifier: Modifier = Modifier,
    containerColor: Color,
    records: List<SelfJournalRecordResource>,
    onCreate: () -> Unit,
    onClick: (Int) -> Unit
) {
    if (records.isEmpty())
        NotFoundVertical(
            modifier = modifier,
            containerColor = containerColor,
            title = Res.string.write_your_first_journal_to_see_journal_history_lets_do_it_now_for_better_mental_health,
            subtitle = Res.string.write_journal,
            image = Drawables.Images.SelfJournalHistory,
            onClick = onCreate
        )
    else
        Column(modifier = modifier) {
            records.forEach { record ->
                JournalHistoryCard(
                    containerColor = containerColor,
                    record = record,
                    onClick = onClick
                )
            }
        }
}