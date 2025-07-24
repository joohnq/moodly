package com.joohnq.onboarding.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.user.impl.ui.resource.MedicationsSupplementsResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MedicationsSupplementsRadioButtonPrescribedMedicationsNotSelectedPreview() {
    MedicationsSupplementsRadioButton(
        text = "Option",
        icon = MedicationsSupplementsResource.PrescribedMedications.icon,
        selected = false,
    )
}

@Preview
@Composable
fun MedicationsSupplementsRadioButtonPrescribedMedicationsSelectedPreview() {
    MedicationsSupplementsRadioButton(
        text = "Option",
        icon = MedicationsSupplementsResource.PrescribedMedications.icon,
        selected = true,
    )
}

@Preview
@Composable
fun MedicationsSupplementsRadioButtonOverTheCounterSupplementsNotSelectedPreview() {
    MedicationsSupplementsRadioButton(
        text = "Option",
        icon = MedicationsSupplementsResource.OverTheCounterSupplements.icon,
        selected = false,
    )
}

@Preview
@Composable
fun MedicationsSupplementsRadioButtonOverTheCounterSupplementsSelectedPreview() {
    MedicationsSupplementsRadioButton(
        text = "Option",
        icon = MedicationsSupplementsResource.OverTheCounterSupplements.icon,
        selected = true,
    )
}

@Preview
@Composable
fun MedicationsSupplementsRadioButtonImNotTakingAnyNotSelectedPreview() {
    MedicationsSupplementsRadioButton(
        text = "Option",
        icon = MedicationsSupplementsResource.ImNotTakingAny.icon,
        selected = false,
    )
}

@Preview
@Composable
fun MedicationsSupplementsRadioButtonImNotTakingAnySelectedPreview() {
    MedicationsSupplementsRadioButton(
        text = "Option",
        icon = MedicationsSupplementsResource.ImNotTakingAny.icon,
        selected = true,
    )
}

@Preview
@Composable
fun MedicationsSupplementsRadioButtonPreferNotToSayNotSelectedPreview() {
    MedicationsSupplementsRadioButton(
        text = "Option",
        icon = MedicationsSupplementsResource.PreferNotToSay.icon,
        selected = false,
    )
}

@Preview
@Composable
fun MedicationsSupplementsRadioButtonPreferNotToSaySelectedPreview() {
    MedicationsSupplementsRadioButton(
        text = "Option",
        icon = MedicationsSupplementsResource.PreferNotToSay.icon,
        selected = true,
    )
}