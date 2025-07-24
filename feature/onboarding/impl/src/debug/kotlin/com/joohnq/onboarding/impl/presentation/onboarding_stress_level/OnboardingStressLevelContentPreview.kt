package com.joohnq.onboarding.impl.presentation.onboarding_stress_level

import androidx.compose.runtime.Composable
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource.Companion.stressLevelRecordResourceFivePreview
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource.Companion.stressLevelRecordResourceFourPreview
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource.Companion.stressLevelRecordResourceOnePreview
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource.Companion.stressLevelRecordResourceThreePreview
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource.Companion.stressLevelRecordResourceTwoPreview
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource.Companion.stressLevelRecordResourceWithStressorsPreview
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnboardingStressLevelContentOnePreview() {
    OnboardingStressLevelContent(
        state = stressLevelRecordResourceOnePreview,
    )
}

@Preview
@Composable
fun OnboardingStressLevelContentTwoPreview() {
    OnboardingStressLevelContent(
        state = stressLevelRecordResourceTwoPreview,
    )
}

@Preview
@Composable
fun OnboardingStressLevelContentThreePreview() {
    OnboardingStressLevelContent(
        state = stressLevelRecordResourceThreePreview,
    )
}

@Preview
@Composable
fun OnboardingStressLevelContentFourPreview() {
    OnboardingStressLevelContent(
        state = stressLevelRecordResourceFourPreview,
    )
}

@Preview
@Composable
fun OnboardingStressLevelContentFivePreview() {
    OnboardingStressLevelContent(
        state = stressLevelRecordResourceFivePreview,
    )
}

@Preview
@Composable
fun OnboardingStressLevelContentWithStressorsPreview() {
    OnboardingStressLevelContent(
        state = stressLevelRecordResourceWithStressorsPreview,
    )
}