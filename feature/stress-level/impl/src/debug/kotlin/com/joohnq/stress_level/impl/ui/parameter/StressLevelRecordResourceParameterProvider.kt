package com.joohnq.stress_level.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource

class StressLevelRecordResourceParameterProvider : PreviewParameterProvider<StressLevelRecordResource> {
    override val values =
        sequenceOf(
            StressLevelRecordResource.Companion.stressLevelRecordResourceOnePreview,
            StressLevelRecordResource.Companion.stressLevelRecordResourceTwoPreview,
            StressLevelRecordResource.Companion.stressLevelRecordResourceThreePreview,
            StressLevelRecordResource.Companion.stressLevelRecordResourceFourPreview,
            StressLevelRecordResource.Companion.stressLevelRecordResourceFivePreview
        )
}
