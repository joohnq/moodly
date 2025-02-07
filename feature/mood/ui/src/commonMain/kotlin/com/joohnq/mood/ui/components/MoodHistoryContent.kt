package com.joohnq.mood.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoodHistoryContent(
    modifier: Modifier = Modifier,
    containerColor: Color,
    records: List<MoodRecordResource>,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        records.forEach { record ->
            MoodHistoryCard(
                containerColor = containerColor,
                record = record,
            )
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