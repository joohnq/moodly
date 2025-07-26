package com.joohnq.onboarding.impl.presentation.onboarding_sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.joohnq.onboarding.impl.ui.presentation.onboarding_sleep_quality.OnboardingSleepQualityContent
import com.joohnq.sleep_quality.impl.ui.parameter.SleepQualityRecordResourceParameterProvider
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource.Companion.sleepQualityRecordExcellentPreview
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnboardingSleepQualityContentPreview(
    @PreviewParameter(SleepQualityRecordResourceParameterProvider::class)
    item: SleepQualityRecordResource,
) {
    OnboardingSleepQualityContent(
        state = item,
        sliderValue = 1f
    )
}

@Preview
@Composable
fun OnboardingSleepQualityContentSlideHalfPreview() {
    OnboardingSleepQualityContent(
        state = sleepQualityRecordExcellentPreview,
        sliderValue = 0.5f
    )
}

@Preview
@Composable
fun OnboardingSleepQualityContentSlideZeroPreview() {
    OnboardingSleepQualityContent(
        state = sleepQualityRecordExcellentPreview,
        sliderValue = 0f
    )
}
