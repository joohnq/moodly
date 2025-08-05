package com.joohnq.onboarding.impl.presentation.onboarding_stress_level

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.joohnq.onboarding.impl.ui.presentation.onboarding_stress_level.OnboardingStressLevelContent
import com.joohnq.stress_level.impl.ui.parameter.StressLevelRecordResourceParameterProvider
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview(
    @PreviewParameter(StressLevelRecordResourceParameterProvider::class)
    item: StressLevelRecordResource,
) {
    OnboardingStressLevelContent(
        state = item
    )
}
