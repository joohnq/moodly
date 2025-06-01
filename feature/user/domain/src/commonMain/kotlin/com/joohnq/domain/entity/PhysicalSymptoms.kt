package com.joohnq.domain.entity

import com.joohnq.domain.property.PhysicalSymptomsPropertiesInterface
import kotlinx.serialization.Serializable

@Serializable
sealed class PhysicalSymptoms(
    override val id: Int,
) : PhysicalSymptomsPropertiesInterface {
    data object YesVeryPainful : PhysicalSymptoms(YES_VERY_PAINFUL.id)
    data object No : PhysicalSymptoms(NO.id)
    data object YesJustABit : PhysicalSymptoms(YES_JUST_A_BIT.id)

    companion object {
        val YES_VERY_PAINFUL = PhysicalSymptomsProperties(0)
        val NO = PhysicalSymptomsProperties(1)
        val YES_JUST_A_BIT = PhysicalSymptomsProperties(2)

        fun from(id: Int): PhysicalSymptoms = when (id) {
            YES_VERY_PAINFUL.id -> YesVeryPainful
            NO.id -> No
            YES_JUST_A_BIT.id -> YesJustABit
            else -> throw IllegalArgumentException("Invalid PhysicalSymptoms id: $id")
        }
    }
}

