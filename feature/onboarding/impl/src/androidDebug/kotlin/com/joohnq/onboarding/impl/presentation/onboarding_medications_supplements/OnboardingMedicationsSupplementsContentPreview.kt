package com.joohnq.onboarding.impl.presentation.onboarding_medications_supplements

import androidx.compose.runtime.Composable
import com.joohnq.user.impl.ui.resource.MedicationsSupplementsResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun OnboardingMedicationsSupplementsContentPrescribedMedicationsPreview(
) {
    OnboardingMedicationsSupplementsContent(
        state = MedicationsSupplementsResource.PrescribedMedications
    )
}

@Preview
@Composable
fun OnboardingMedicationsSupplementsContentOverTheCounterSupplementsPreview(
) {
    OnboardingMedicationsSupplementsContent(
        state = MedicationsSupplementsResource.OverTheCounterSupplements
    )
}

@Preview
@Composable
fun OnboardingMedicationsSupplementsContentImNotTakingAnyPreview(
) {
    OnboardingMedicationsSupplementsContent(
        state = MedicationsSupplementsResource.ImNotTakingAny
    )
}

@Preview
@Composable
fun OnboardingMedicationsSupplementsContentPreferNotToSayPreview(
) {
    OnboardingMedicationsSupplementsContent(
        state = MedicationsSupplementsResource.PreferNotToSay
    )
}