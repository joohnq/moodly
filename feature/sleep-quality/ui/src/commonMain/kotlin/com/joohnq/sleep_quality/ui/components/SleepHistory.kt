package com.joohnq.sleep_quality.ui.components


import SleepQualityHistoryCard
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
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord

@Composable
fun SleepHistory(
    modifier: Modifier = Modifier,
    containerColor: Color = Colors.White,
    records: List<SleepQualityRecord>,
    onClick: () -> Unit
) {
    if (records.isEmpty())
        NotFoundHorizontal(
            modifier = modifier,
            containerColor = containerColor,
            title = Res.string.you_havent_set_up_any_mental_sleep_yet,
            subtitle = Res.string.set_up_sleep,
            image = Drawables.Images.SleepWomanTiredIllustration,
            onClick = onClick
        )
    else
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            records.forEach { record ->
                SwipeTorRevealCard(
                    modifier = modifier,
                    onAction = {}
                ) { modifier ->
                    SleepQualityHistoryCard(
                        modifier = modifier,
                        record = record,
                        onClick = { },
                    )
                }
            }
        }
}