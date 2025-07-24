package com.joohnq.stress_level.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource

class ListStressLevelRecordResourceParameterProvider :
    PreviewParameterProvider<List<StressLevelRecordResource>> {
    override val values = sequenceOf(
        StressLevelRecordResource.allStressLevelRecordResourcePreview,
        emptyList()
    )
}