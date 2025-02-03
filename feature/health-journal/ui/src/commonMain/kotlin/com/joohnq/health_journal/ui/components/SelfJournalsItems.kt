package com.joohnq.health_journal.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.shared_resources.components.IsEmpty

@Composable
fun SelfJournalsItems(
    items: List<HealthJournalRecord>?,
    onClick: (Int) -> Unit,
    onDelete: (Int) -> Unit,
) {
    if (items.isNullOrEmpty())
        IsEmpty()
    else
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(items) { i, healthJournal ->
                SelfJournalCard(
                    isNotFirst = i != 0,
                    isNotLast = i != items.lastIndex,
                    record = healthJournal,
                    onClick = onClick,
                    onDelete = {
                        onDelete(healthJournal.id)
                    }
                )
            }
        }
}