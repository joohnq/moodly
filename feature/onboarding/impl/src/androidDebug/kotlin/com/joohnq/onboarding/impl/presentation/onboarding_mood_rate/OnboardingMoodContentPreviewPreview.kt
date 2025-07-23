package com.joohnq.onboarding.impl.presentation.onboarding_mood_rate

import androidx.compose.runtime.Composable
import com.joohnq.mood.impl.ui.resource.MoodRecordResource.Companion.moodRecordResourceDepressedPreview
import com.joohnq.mood.impl.ui.resource.MoodRecordResource.Companion.moodRecordResourceHappyPreview
import com.joohnq.mood.impl.ui.resource.MoodRecordResource.Companion.moodRecordResourceNeutralPreview
import com.joohnq.mood.impl.ui.resource.MoodRecordResource.Companion.moodRecordResourceOverjoyedPreview
import com.joohnq.mood.impl.ui.resource.MoodRecordResource.Companion.moodRecordResourceSadPreview
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnboardingMoodRateContentDepressedPreview() {
    OnboardingMoodRateContent(
        state = moodRecordResourceDepressedPreview,
    )
}

@Preview
@Composable
fun OnboardingMoodRateContentSadPreview() {
    OnboardingMoodRateContent(
        state = moodRecordResourceSadPreview,
    )
}

@Preview
@Composable
fun OnboardingMoodRateContentNeutralPreview() {
    OnboardingMoodRateContent(
        state = moodRecordResourceNeutralPreview,
    )
}

@Preview
@Composable
fun OnboardingMoodRateContentHappyPreview() {
    OnboardingMoodRateContent(
        state = moodRecordResourceHappyPreview,
    )
}

@Preview
@Composable
fun OnboardingMoodRateContentOverjoyedPreview() {
    OnboardingMoodRateContent(
        state = moodRecordResourceOverjoyedPreview,
    )
}