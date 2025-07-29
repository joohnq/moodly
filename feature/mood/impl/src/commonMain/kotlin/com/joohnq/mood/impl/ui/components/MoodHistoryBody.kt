package com.joohnq.mood.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.impl.ui.resource.MoodRecordResource

@Composable
fun MoodHistoryBody(
    modifier: Modifier = Modifier,
    records: List<MoodRecordResource>,
    onDelete: (Int) -> Unit = {},
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        records.forEach { record ->
            MoodHistoryCard(
                onDelete = onDelete,
                record = record
            )
        }
    }
}
