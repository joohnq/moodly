package com.joohnq.onboarding.impl.presentation.onboarding_medications_supplements

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.joohnq.onboarding.impl.ui.presentation.onboarding_medications_supplements.OnboardingMedicationsSupplementsContent
import com.joohnq.user.impl.ui.parameter.MedicationsSupplementsResourceParameterProvider
import com.joohnq.user.impl.ui.resource.MedicationsSupplementsResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview(
    @PreviewParameter(MedicationsSupplementsResourceParameterProvider::class)
    item: MedicationsSupplementsResource,
) {
    OnboardingMedicationsSupplementsContent(
        state = item
    )
}
