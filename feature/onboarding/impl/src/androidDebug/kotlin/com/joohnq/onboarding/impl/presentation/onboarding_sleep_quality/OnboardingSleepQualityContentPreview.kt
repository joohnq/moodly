package com.joohnq.onboarding.impl.presentation.onboarding_sleep_quality

import androidx.compose.runtime.Composable
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource.Companion.sleepQualityRecordExcellentPreview
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource.Companion.sleepQualityRecordFairPreview
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource.Companion.sleepQualityRecordGoodPreview
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource.Companion.sleepQualityRecordPoorPreview
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource.Companion.sleepQualityRecordWorstPreview
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnboardingSleepQualityContentWorstPreview() {
    OnboardingSleepQualityContent(
        state = sleepQualityRecordWorstPreview,
        sliderValue = 1f,
    )
}

@Preview
@Composable
fun OnboardingSleepQualityContentPoorPreview() {
    OnboardingSleepQualityContent(
        state = sleepQualityRecordPoorPreview,
        sliderValue = 1f,
    )
}

@Preview
@Composable
fun OnboardingSleepQualityContentFairPreview() {
    OnboardingSleepQualityContent(
        state = sleepQualityRecordFairPreview,
        sliderValue = 1f,
    )
}

@Preview
@Composable
fun OnboardingSleepQualityContentGoodPreview() {
    OnboardingSleepQualityContent(
        state = sleepQualityRecordGoodPreview,
        sliderValue = 1f,
    )
}

@Preview
@Composable
fun OnboardingSleepQualityContentExcellentPreview() {
    OnboardingSleepQualityContent(
        state = sleepQualityRecordExcellentPreview,
        sliderValue = 1f,
    )
}


@Preview
@Composable
fun OnboardingSleepQualityContentSlideHalfPreview() {
    OnboardingSleepQualityContent(
        state = sleepQualityRecordExcellentPreview,
        sliderValue = 0.5f,
    )
}

@Preview
@Composable
fun OnboardingSleepQualityContentSlideZeroPreview() {
    OnboardingSleepQualityContent(
        state = sleepQualityRecordExcellentPreview,
        sliderValue = 0f,
    )
}