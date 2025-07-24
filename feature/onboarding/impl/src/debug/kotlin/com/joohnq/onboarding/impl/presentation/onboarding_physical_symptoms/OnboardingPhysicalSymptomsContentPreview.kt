package com.joohnq.onboarding.impl.presentation.onboarding_physical_symptoms

import androidx.compose.runtime.Composable
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnboardingPhysicalSymptomsContentNoPreview() {
    OnboardingPhysicalSymptomsContent(
        state = PhysicalSymptomsResource.No,
    )
}

@Preview
@Composable
fun OnboardingPhysicalSymptomsContentYesJustABitPreview() {
    OnboardingPhysicalSymptomsContent(
        state = PhysicalSymptomsResource.YesJustABit,
    )
}

@Preview
@Composable
fun OnboardingPhysicalSymptomsContentYesVeryPainfulPreview() {
    OnboardingPhysicalSymptomsContent(
        state = PhysicalSymptomsResource.YesVeryPainful,
    )
}