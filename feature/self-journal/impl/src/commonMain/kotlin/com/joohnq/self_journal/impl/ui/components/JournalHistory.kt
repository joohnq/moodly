package com.joohnq.self_journal.impl.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.NotFoundVerticalLayout
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.write_journal
import com.joohnq.shared_resources.write_your_first_journal_to_see_journal_history_lets_do_it_now_for_better_mental_health

@Composable
fun JournalHistory(
    modifier: Modifier = Modifier,
    records: List<SelfJournalRecordResource>,
    onCreate: () -> Unit = {},
    onClick: (Int) -> Unit = {}
) {
    if (records.isEmpty())
        NotFoundVerticalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            title = Res.string.write_your_first_journal_to_see_journal_history_lets_do_it_now_for_better_mental_health,
            subtitle = Res.string.write_journal,
            image = Drawables.Images.SelfJournalHistory,
            onClick = onCreate
        )
    else
        Column(modifier = modifier) {
            records.forEach { record ->
                JournalHistoryCard(
                    record = record,
                    onClick = onClick
                )
            }
        }
}