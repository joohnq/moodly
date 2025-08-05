package com.joohnq.stress_level.overview.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.NotFoundVerticalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.log_stress
import com.joohnq.shared_resources.stress_insight
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.you_dont_have_enough_data_to_show_insights
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toPair
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource

@Composable
fun StressLevelOverviewInsight(
    modifier: Modifier = Modifier,
    records: List<StressLevelRecordResource>,
    onCreate: () -> Unit = {},
) {
    val stressors = records.toPair()

    SectionHeader(
        modifier = modifier,
        title = Res.string.stress_insight
    )
    if (stressors.isEmpty()) {
        NotFoundVerticalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            title = Res.string.you_dont_have_enough_data_to_show_insights,
            subtitle = Res.string.log_stress,
            image = Drawables.Images.StressLevelInsight,
            onClick = onCreate
        )
    } else {
        StressLevelOverviewInsightCard(
            modifier = modifier,
            stressors = stressors,
            mostActive = stressors.last().first
        )
    }
}