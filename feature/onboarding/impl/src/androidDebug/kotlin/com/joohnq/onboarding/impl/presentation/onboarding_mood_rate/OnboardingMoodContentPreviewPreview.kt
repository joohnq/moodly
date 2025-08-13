package com.joohnq.onboarding.impl.presentation.onboarding_mood_rate

import androidx.compose.runtime.Composable
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.parameter.MoodRecordResourceParameterProvider
import com.joohnq.onboarding.impl.ui.presentation.onboarding_mood_rate.OnboardingMoodRateContent
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(MoodRecordResourceParameterProvider::class)
    item: MoodRecordResource,
) {
    OnboardingMoodRateContent(
        state = item
    )
}
