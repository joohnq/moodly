package com.joohnq.mood.add.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.add.ui.resource.MoodRecordResource

@Composable
fun MoodHistoryBody(
    modifier: Modifier = Modifier,
    items: List<MoodRecordResource>,
    onDelete: (Long) -> Unit = {},
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items.forEach { record ->
            MoodHistoryCard(
                onDelete = onDelete,
                item = record
            )
        }
    }
}
