package com.joohnq.user.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource

class PhysicalSymptomsResourceParameterProvider : PreviewParameterProvider<PhysicalSymptomsResource> {
    override val values =
        sequenceOf(
            PhysicalSymptomsResource.No,
            PhysicalSymptomsResource.YesJustABit,
            PhysicalSymptomsResource.YesVeryPainful
        )
}
