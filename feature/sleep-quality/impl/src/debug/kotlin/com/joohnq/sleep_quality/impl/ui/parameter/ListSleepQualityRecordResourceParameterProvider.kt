package com.joohnq.sleep_quality.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource

class ListSleepQualityRecordResourceParameterProvider :
    PreviewParameterProvider<List<SleepQualityRecordResource>> {
    override val values = sequenceOf(
        SleepQualityRecordResource.allSleepQualityRecordResource,
        emptyList()
    )
}