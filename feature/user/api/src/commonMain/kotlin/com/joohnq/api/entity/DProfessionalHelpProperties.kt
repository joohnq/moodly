package com.joohnq.api.entity

import com.joohnq.api.property.ProfessionalHelpProperties

data class DProfessionalHelpProperties(
    override val id: Int,
    override val value: Boolean,
) : ProfessionalHelpProperties
