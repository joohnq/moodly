package com.joohnq.stress_level.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_journal
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.SwipeTorRevealCard
import com.joohnq.shared_resources.lets_set_up_daily_stress_level
import com.joohnq.shared_resources.stress_history
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressHistory(
    modifier: Modifier = Modifier,
    containerColor: Color,
    records: List<StressLevelRecordResource>,
    onAddStressLevel: () -> Unit,
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.stress_history,
        onSeeMore = {}
    )
    if (records.isEmpty())
        NotFoundHorizontal(
            modifier = modifier,
            containerColor = containerColor,
            title = Res.string.lets_set_up_daily_stress_level,
            subtitle = Res.string.add_new_journal,
            image = Drawables.Images.StressLevelHistory,
            onClick = onAddStressLevel
        )
    else
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            records.forEach { record ->
                SwipeTorRevealCard(
                    modifier = modifier,
                    onAction = {}
                ) { modifier ->
                    StressLevelHistoryCard(
                        modifier = modifier.background(color = containerColor),
                        containerColor = containerColor,
                        record = record,
                    )
                }
            }
        }
}

@Preview
@Composable
fun StressHistoryPreviewEmpty() {
    StressHistory(
        containerColor = Colors.White,
        records = listOf(),
        onAddStressLevel = {}
    )
}

@Preview
@Composable
fun StressHistoryPreview() {
    StressHistory(
        containerColor = Colors.White,
        records = listOf(
            StressLevelRecordResource(),
            StressLevelRecordResource()
        ),
        onAddStressLevel = {}
    )
}