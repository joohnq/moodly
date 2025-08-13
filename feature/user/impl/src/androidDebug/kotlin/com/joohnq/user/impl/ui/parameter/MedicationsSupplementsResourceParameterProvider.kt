package com.joohnq.user.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.user.impl.ui.resource.MedicationsSupplementsResource

class MedicationsSupplementsResourceParameterProvider : PreviewParameterProvider<MedicationsSupplementsResource> {
    override val values =
        sequenceOf(
            MedicationsSupplementsResource.PrescribedMedications,
            MedicationsSupplementsResource.OverTheCounterSupplements,
            MedicationsSupplementsResource.ImNotTakingAny,
            MedicationsSupplementsResource.PreferNotToSay
        )
}
