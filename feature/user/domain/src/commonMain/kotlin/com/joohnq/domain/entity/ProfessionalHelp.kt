package com.joohnq.domain.entity

import com.joohnq.domain.ProfessionalHelpProperties
import kotlinx.serialization.Serializable

sealed class ProfessionalHelp(
    override val id: Int,
    override val value: Boolean,
) : ProfessionalHelpProperties {
    data object Yes : ProfessionalHelp(YES.id, YES.value)
    data object No : ProfessionalHelp(NO.id, NO.value)

    companion object {
        val YES = DProfessionalHelpProperties(1, true)
        val NO = DProfessionalHelpProperties(0, false)

        fun toValue(src: Int): ProfessionalHelp = when (src) {
            YES.id -> Yes
            NO.id -> No
            else -> throw IllegalArgumentException("Unknown professional help option: $src")
        }

        fun ProfessionalHelp?.fromValue(): Int = this?.id ?: -1

        fun getAll(): List<ProfessionalHelp> = listOf(Yes, No)
    }
}
