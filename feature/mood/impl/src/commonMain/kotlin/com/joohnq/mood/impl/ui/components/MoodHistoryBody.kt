package com.joohnq.mood.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.components.layout.SwipeableCardLayout

@Composable
fun MoodHistoryBody(
    modifier: Modifier = Modifier,
    records: List<MoodRecordResource>,
    onDelete: (Int) -> Unit = {}
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        records.forEach { record ->
            SwipeableCardLayout(
                modifier = Modifier.fillMaxWidth(),
                onAction = { onDelete(record.id) }
            ) { modifier ->
                MoodHistoryCard(
                    modifier = modifier,
                    record = record
                )
            }
        }
    }
}
