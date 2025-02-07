package com.joohnq.self_journal.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.components.IsEmpty

@Composable
fun SelfJournalsItems(
    items: List<SelfJournalRecordResource>?,
    onClick: (Int) -> Unit,
    onDelete: (Int) -> Unit,
) {
    if (items.isNullOrEmpty())
        IsEmpty()
    else
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(items) { i, selfJournal ->
                SelfJournalCard(
                    isNotFirst = i != 0,
                    isNotLast = i != items.lastIndex,
                    record = selfJournal,
                    onClick = onClick,
                    onDelete = {
                        onDelete(selfJournal.id)
                    }
                )
            }
        }
}