package com.joohnq.self_journal.impl.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.components.IsEmpty
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.ui.mapper.itemsIndexed
import kotlinx.datetime.LocalDate

@Composable
fun SelfJournalsHistoryCards(
    modifier: Modifier = Modifier,
    records: Map<LocalDate, List<SelfJournalRecordResource>>,
    onClick: (Int) -> Unit = {},
    onDelete: (Int) -> Unit = {},
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(
            items = records,
            title = {

            },
            empty = {
                IsEmpty()
            }
        ) { i, lastIndex, record ->
            SelfJournalHistoryCard(
                modifier = modifier,
                isNotFirst = i != 0,
                isNotLast = i != lastIndex,
                record = record,
                onClick = onClick,
                onDelete = {
                    onDelete(record.id)
                }
            )
        }
    }
}