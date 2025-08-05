package com.joohnq.stress_level.impl.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_journal
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.lets_set_up_daily_stress_level
import com.joohnq.shared_resources.stress_history
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource

@Composable
fun StressLevelHistory(
    modifier: Modifier = Modifier,
    records: List<StressLevelRecordResource>,
    onDelete: (Int) -> Unit = {},
    onAddStressLevel: () -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.stress_history,
        onSeeMore = {}
    )
    if (records.isEmpty()) {
        NotFoundHorizontalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            title = Res.string.lets_set_up_daily_stress_level,
            subtitle = Res.string.add_new_journal,
            image = Drawables.Images.StressLevelHistory,
            onClick = onAddStressLevel
        )
    } else {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            records.forEach { record ->
                StressLevelHistoryCard(
                    record = record,
                    onDelete = { onDelete(record.id) }
                )
            }
        }
    }
}
