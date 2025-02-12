package com.joohnq.stress_level.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.NotFoundVertical
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.lets_log_your_first_stress_level_to_get_more_insights_on_data
import com.joohnq.shared_resources.log_stress
import com.joohnq.shared_resources.stress_insight
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource

@Composable
fun StressInsight(
    modifier: Modifier = Modifier,
    containerColor: Color = Colors.Gray5,
    records: List<StressLevelRecordResource>,
    onCreate: () -> Unit = {}
) {
    val stressors = records
        .flatMap { it.stressors }
        .groupBy { it }
        .map { it.key to it.value.size }
        .sortedBy { it.second }

    SectionHeader(
        modifier = modifier,
        title = Res.string.stress_insight,
    )
    if (stressors.isEmpty())
        NotFoundVertical(
            modifier = modifier,
            containerColor = containerColor,
            title = Res.string.lets_log_your_first_stress_level_to_get_more_insights_on_data,
            subtitle = Res.string.log_stress,
            image = Drawables.Images.StressLevelInsight,
            onClick = onCreate
        )
    else
        StressInsightCard(
            modifier = modifier,
            containerColor = containerColor,
            stressors = stressors,
            mostActive = stressors.last().first
        )
}