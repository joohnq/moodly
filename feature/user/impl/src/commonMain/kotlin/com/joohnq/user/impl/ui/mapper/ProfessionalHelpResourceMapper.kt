package com.joohnq.user.impl.ui.mapper

import com.joohnq.api.entity.ProfessionalHelp
import com.joohnq.user.impl.ui.resource.ProfessionalHelpResource
import com.joohnq.user.impl.ui.resource.ProfessionalHelpResource.No
import com.joohnq.user.impl.ui.resource.ProfessionalHelpResource.Yes

fun getAllProfessionalHelpResource(): List<ProfessionalHelpResource> = listOf(Yes, No)

fun ProfessionalHelp.toResource(): ProfessionalHelpResource = when (this) {
    ProfessionalHelp.Yes -> Yes
    ProfessionalHelp.No -> No
}

fun ProfessionalHelpResource.toDomain(): ProfessionalHelp = when (this) {
    Yes -> ProfessionalHelp.Yes
    No -> ProfessionalHelp.No
}