package com.joohnq.onboarding.impl.presentation.onboarding_physical_symptoms

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.joohnq.onboarding.impl.ui.presentation.onboarding_physical_symptoms.OnboardingPhysicalSymptomsContent
import com.joohnq.user.impl.ui.parameter.PhysicalSymptomsResourceParameterProvider
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnboardingPhysicalSymptomsContentPreview(
    @PreviewParameter(PhysicalSymptomsResourceParameterProvider::class) item: PhysicalSymptomsResource,
) {
    OnboardingPhysicalSymptomsContent(
        state = item
    )
}
