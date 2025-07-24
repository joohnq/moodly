package com.joohnq.stress_level.impl.ui.presentation.stress_stressors

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.presentation.add_stress_level.viewmodel.AddingStressLevelState
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StressStressorsContentOnePreview() {
    StressStressorsContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceOnePreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun StressStressorsContentTwoPreview() {
    StressStressorsContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceTwoPreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun StressStressorsContentThreePreview() {
    StressStressorsContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceThreePreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun StressStressorsContentFourPreview() {
    StressStressorsContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceFourPreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun StressStressorsContentFivePreview() {
    StressStressorsContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceFivePreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun StressStressorsContentWithStressorsPreview() {
    StressStressorsContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceWithStressorsPreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun StressStressorsContentHalfSliderValuePreview() {
    StressStressorsContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceFivePreview,
            sliderValue = 0.5f
        ),
    )
}