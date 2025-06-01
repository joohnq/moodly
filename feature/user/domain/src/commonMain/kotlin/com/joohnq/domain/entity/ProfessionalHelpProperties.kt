package com.joohnq.domain.entity

import com.joohnq.domain.property.ProfessionalHelpPropertiesInterface

data class ProfessionalHelpProperties(override val id: Int, override val value: Boolean) :
    ProfessionalHelpPropertiesInterface
