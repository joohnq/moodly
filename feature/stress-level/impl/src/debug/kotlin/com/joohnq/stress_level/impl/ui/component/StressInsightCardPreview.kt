package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toPair
import com.joohnq.stress_level.impl.ui.parameter.ListStressLevelRecordResourceParameterProvider
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressInsightCardWorkPreview(
    @PreviewParameter(ListStressLevelRecordResourceParameterProvider::class)
    items: List<StressLevelRecordResource>,
) {
    StressInsightCard(
        stressors = items.toPair(),
        mostActive = StressorResource.Work
    )
}
