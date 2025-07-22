package com.joohnq.mood.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.components.SwipeTorRevealCard
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoodHistoryContent(
    modifier: Modifier = Modifier,
    containerColor: Color,
    records: List<MoodRecordResource>,
    onDelete: (Int) -> Unit = {},
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        records.forEach { record ->
            SwipeTorRevealCard(
                modifier = Modifier.fillMaxWidth(),
                onAction = { onDelete(record.id) }
            ) { modifier ->
                MoodHistoryCard(
                    modifier = modifier,
                    containerColor = containerColor,
                    record = record,
                )
            }

        }
    }
}

@Preview
@Composable
fun MoodHistoryContentPreview() {
    MoodHistoryContent(
        modifier = Modifier,
        containerColor = Colors.White,
        records = listOf(
            MoodRecordResource(),
            MoodRecordResource(),
            MoodRecordResource(),
        ),
    )
}