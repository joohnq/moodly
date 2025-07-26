package com.joohnq.onboarding.impl.presentation.onboarding_mood_rate

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.parameter.MoodRecordResourceParameterProvider
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.onboarding.impl.ui.presentation.onboarding_mood_rate.OnboardingMoodRateContent
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun OnboardingMoodRateContentDepressedPreview(
    @PreviewParameter(MoodRecordResourceParameterProvider::class)
    item: MoodRecordResource
) {
    OnboardingMoodRateContent(
        state = item
    )
}