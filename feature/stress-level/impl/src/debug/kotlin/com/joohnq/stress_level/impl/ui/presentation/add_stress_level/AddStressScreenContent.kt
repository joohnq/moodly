package com.joohnq.stress_level.impl.ui.presentation.add_stress_level

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.presentation.add_stress_level.viewmodel.AddingStressLevelState
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AddStressLevelScreenContentOnePreview() {
    AddStressLevelScreenContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceOnePreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun AddStressLevelScreenContentTwoPreview() {
    AddStressLevelScreenContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceTwoPreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun AddStressLevelScreenContentThreePreview() {
    AddStressLevelScreenContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceThreePreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun AddStressLevelScreenContentFourPreview() {
    AddStressLevelScreenContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceFourPreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun AddStressLevelScreenContentFivePreview() {
    AddStressLevelScreenContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceFivePreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun AddStressLevelScreenContentWithStressorsPreview() {
    AddStressLevelScreenContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceWithStressorsPreview,
            sliderValue = 1f
        ),
    )
}

@Preview
@Composable
fun AddStressLevelScreenContentHalfSliderValuePreview() {
    AddStressLevelScreenContent(
        state = AddingStressLevelState(
            record = StressLevelRecordResource.stressLevelRecordResourceWithStressorsPreview,
            sliderValue = 0.5f
        ),
    )
}