package com.joohnq.api.entity

import com.joohnq.api.property.ProfessionalHelpProperties

data class DProfessionalHelpProperties(
    override val id: Long,
    override val value: Boolean,
) : ProfessionalHelpProperties
