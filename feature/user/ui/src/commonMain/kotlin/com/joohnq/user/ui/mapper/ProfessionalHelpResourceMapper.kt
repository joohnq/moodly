package com.joohnq.user.ui.mapper

import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.user.ui.resource.ProfessionalHelpResource
import com.joohnq.user.ui.resource.ProfessionalHelpResource.No
import com.joohnq.user.ui.resource.ProfessionalHelpResource.Yes

fun getAllProfessionalHelpResource(): List<ProfessionalHelpResource> = listOf(Yes, No)

fun ProfessionalHelp.toResource(): ProfessionalHelpResource = when (this) {
    ProfessionalHelp.Yes -> Yes
    ProfessionalHelp.No -> No
}

fun ProfessionalHelpResource.toDomain(): ProfessionalHelp = when (this) {
    Yes -> ProfessionalHelp.Yes
    No -> ProfessionalHelp.No
}