package com.joohnq.self_journal.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.NotFoundVerticalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.journal_history
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.write_journal
import com.joohnq.shared_resources.write_your_first_journal_to_see_journal_history_lets_do_it_now_for_better_mental_health

@Composable
fun SelfJournalOverviewHistory(
    modifier: Modifier = Modifier,
    items: List<SelfJournalRecordResource>,
    onCreate: () -> Unit = {},
    onClick: (Long) -> Unit = {},
    onDelete: (Long) -> Unit = {},
    onSeeMore: () -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_history,
        onSeeMore = onSeeMore
    )

    if (items.isEmpty()) {
        NotFoundVerticalLayout(
            modifier = modifier,
            title = Res.string.write_your_first_journal_to_see_journal_history_lets_do_it_now_for_better_mental_health,
            actionText = Res.string.write_journal,
            image = Drawables.Images.SelfJournalHistory,
            onClick = onCreate
        )
    } else {
        Column(modifier = modifier) {
            items.forEach { item ->
                JournalHistoryCard(
                    item = item,
                    onClick = onClick,
                    onDelete = onDelete
                )
            }
        }
    }
}
