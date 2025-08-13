package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toPair
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.overview.component.StressLevelOverviewInsight
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    StressLevelOverviewInsight(
        stressors = StressLevelRecordResource.allStressLevelRecordResourcePreview.toPair()
    )
}
