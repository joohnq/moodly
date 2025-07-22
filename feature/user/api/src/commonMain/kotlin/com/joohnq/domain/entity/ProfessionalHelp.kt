package com.joohnq.domain.entity

import com.joohnq.domain.property.ProfessionalHelpProperties

sealed class ProfessionalHelp(
    override val id: Int,
    override val value: Boolean,
) : ProfessionalHelpProperties {
    data object Yes : ProfessionalHelp(YES.id, YES.value)
    data object No : ProfessionalHelp(NO.id, NO.value)

    companion object {
        val YES = DProfessionalHelpProperties(1, true)
        val NO = DProfessionalHelpProperties(0, false)
    }
}
