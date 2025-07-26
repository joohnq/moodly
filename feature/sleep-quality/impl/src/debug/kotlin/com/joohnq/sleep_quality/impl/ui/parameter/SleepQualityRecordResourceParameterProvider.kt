package com.joohnq.sleep_quality.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource

class SleepQualityRecordResourceParameterProvider : PreviewParameterProvider<SleepQualityRecordResource> {
    override val values =
        sequenceOf(
            SleepQualityRecordResource.sleepQualityRecordWorstPreview,
            SleepQualityRecordResource.sleepQualityRecordPoorPreview,
            SleepQualityRecordResource.sleepQualityRecordFairPreview,
            SleepQualityRecordResource.sleepQualityRecordGoodPreview,
            SleepQualityRecordResource.sleepQualityRecordExcellentPreview
        )
}
