package com.joohnq.domain.entity

import com.joohnq.domain.property.ProfessionalHelpPropertiesInterface
import kotlinx.serialization.Serializable

@Serializable
sealed class ProfessionalHelp(
    override val id: Int,
    override val value: Boolean,
) : ProfessionalHelpPropertiesInterface {
    data object Yes : ProfessionalHelp(YES.id, YES.value)
    data object No : ProfessionalHelp(NO.id, NO.value)

    companion object {
        val YES = ProfessionalHelpProperties(1, true)
        val NO = ProfessionalHelpProperties(0, false)

        fun from(id: Int): ProfessionalHelp = when (id) {
            YES.id -> Yes
            NO.id -> No
            else -> throw IllegalArgumentException("Invalid id: $id")
        }
    }
}
