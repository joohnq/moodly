package com.joohnq.stress_level.add.presentation

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.parameter.StressLevelRecordResourceParameterProvider
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(StressLevelRecordResourceParameterProvider::class)
    item: StressLevelRecordResource,
) {
    StressStressorsContent(
        state =
            AddStressLevelContract.State(
                item = item,
                sliderValue = 1f
            )
    )
}

@Preview
@Composable
fun StressStressorsContentHalfSliderValuePreview() {
    StressStressorsContent(
        state =
            AddStressLevelContract.State(
                item = StressLevelRecordResource.stressLevelRecordResourceFivePreview,
                sliderValue = 0.5f
            )
    )
}
