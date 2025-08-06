package com.joohnq.stress_level.overview.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.log_stress_level
import com.joohnq.shared_resources.stress_trigger
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.you_dont_have_enough_sleep_records_yet
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource

@Composable
fun StressLevelOverviewTriggers(
    modifier: Modifier = Modifier,
    items: List<StressLevelRecordResource>,
    onAddStressLevel: () -> Unit = {},
) {
    val stressors = items.flatMap { it.stressors }

    SectionHeader(
        modifier = modifier,
        title = Res.string.stress_trigger
    )
    if (stressors.size < 3) {
        NotFoundHorizontalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            title = Res.string.you_dont_have_enough_sleep_records_yet,
            subtitle = Res.string.log_stress_level,
            image = Drawables.Images.StressLevelTrigger,
            onClick = onAddStressLevel
        )
    } else {
        StressLevelOverviewTriggersCard(
            modifier = modifier,
            stressors = stressors
        )
    }
}