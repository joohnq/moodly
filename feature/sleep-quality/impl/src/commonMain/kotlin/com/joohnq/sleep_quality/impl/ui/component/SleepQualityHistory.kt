package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.set_up_sleep
import com.joohnq.shared_resources.sleep_history
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.you_havent_set_up_any_mental_sleep_yet
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource

@Composable
fun SleepQualityHistory(
    modifier: Modifier = Modifier,
    records: List<SleepQualityRecordResource>,
    onDelete: (Int) -> Unit = {},
    onCreate: () -> Unit = {},
    onSeeMore: () -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.sleep_history,
        onSeeMore = onSeeMore
    )
    if (records.isEmpty()) {
        NotFoundHorizontalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            title = Res.string.you_havent_set_up_any_mental_sleep_yet,
            subtitle = Res.string.set_up_sleep,
            image = Drawables.Images.SleepQualityHistory,
            onClick = onCreate
        )
    } else {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            records.forEach { record ->
                SleepQualityHistoryCard(
                    modifier = modifier,
                    record = record,
                    onDelete = onDelete
                )
            }
        }
    }
}
