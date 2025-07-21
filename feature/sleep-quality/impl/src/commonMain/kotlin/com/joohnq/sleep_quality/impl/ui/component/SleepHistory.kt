package com.joohnq.sleep_quality.ui.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.SwipeTorRevealCard
import com.joohnq.shared_resources.set_up_sleep
import com.joohnq.shared_resources.sleep_history
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.you_havent_set_up_any_mental_sleep_yet
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SleepHistory(
    modifier: Modifier = Modifier,
    containerColor: Color = Colors.White,
    records: List<SleepQualityRecordResource>,
    onDelete: (Int) -> Unit = {},
    onCreate: () -> Unit = {},
    onSeeMore: () -> Unit = {}
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.sleep_history,
        onSeeMore = onSeeMore
    )
    if (records.isEmpty())
        NotFoundHorizontal(
            modifier = modifier,
            containerColor = containerColor,
            title = Res.string.you_havent_set_up_any_mental_sleep_yet,
            subtitle = Res.string.set_up_sleep,
            image = Drawables.Images.SleepQualityHistory,
            onClick = onCreate
        )
    else
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            records.forEach { record ->
                SwipeTorRevealCard(
                    modifier = modifier,
                    onAction = { onDelete(record.id) }
                ) { modifier ->
                    SleepQualityHistoryCard(
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
fun SleepHistoryPreview() {
    SleepHistory(
        records = listOf(
            SleepQualityRecordResource(),
            SleepQualityRecordResource(),
        ),
    )
}