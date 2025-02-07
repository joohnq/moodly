package com.joohnq.mood.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoodHistoryContent(
    modifier: Modifier = Modifier,
    containerColor: Color,
    records: List<MoodRecord>,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        records.forEach { record ->
            val resource = record.mood.toResource()
            MoodHistoryCard(
                containerColor = containerColor,
                resource = resource,
                record = record
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
            MoodRecord(),
            MoodRecord(),
            MoodRecord(),
        ),
    )
}