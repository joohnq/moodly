package com.joohnq.user.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.user.impl.ui.resource.ProfessionalHelpResource

class ProfessionalHelpResourceParameterProvider : PreviewParameterProvider<ProfessionalHelpResource> {
    override val values =
        sequenceOf(
            ProfessionalHelpResource.No,
            ProfessionalHelpResource.Yes
        )
}
