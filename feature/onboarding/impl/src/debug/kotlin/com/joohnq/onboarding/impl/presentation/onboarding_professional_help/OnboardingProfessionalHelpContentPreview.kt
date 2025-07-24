package com.joohnq.onboarding.impl.presentation.onboarding_professional_help

import androidx.compose.runtime.Composable
import com.joohnq.user.impl.ui.resource.ProfessionalHelpResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnboardingProfessionalHelpContentNoPreview() {
    OnboardingProfessionalHelpContent(
        state = ProfessionalHelpResource.No,
    )
}

@Preview
@Composable
fun OnboardingProfessionalHelpContentYesPreview() {
    OnboardingProfessionalHelpContent(
        state = ProfessionalHelpResource.Yes,
    )
}