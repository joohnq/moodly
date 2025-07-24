package com.joohnq.onboarding.impl.presentation.onboarding_professional_help

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.joohnq.user.impl.ui.parameter.ProfessionalHelpResourceParameterProvider
import com.joohnq.user.impl.ui.resource.ProfessionalHelpResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnboardingProfessionalHelpContentPreview(
    @PreviewParameter(ProfessionalHelpResourceParameterProvider ::class) item: ProfessionalHelpResource
) {
    OnboardingProfessionalHelpContent(
        state = item,
    )
}