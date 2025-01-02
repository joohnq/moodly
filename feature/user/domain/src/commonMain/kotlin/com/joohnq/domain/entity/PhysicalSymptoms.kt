package com.joohnq.domain.entity

import com.joohnq.domain.PhysicalSymptomsProperties
import kotlinx.serialization.Serializable

sealed class PhysicalSymptoms(
    override val id: Int,
) : PhysicalSymptomsProperties {
    data object YesVeryPainful : PhysicalSymptoms(YES_VERY_PAINFUL.id)
    data object No : PhysicalSymptoms(NO.id)
    data object YesJustABit : PhysicalSymptoms(YES_JUST_A_BIT.id)

    companion object {
        val YES_VERY_PAINFUL = DPhysicalSymptomsProperties(0)
        val NO = DPhysicalSymptomsProperties(1)
        val YES_JUST_A_BIT = DPhysicalSymptomsProperties(2)

        fun toValue(src: Int): PhysicalSymptoms = when (src) {
            YES_VERY_PAINFUL.id -> YesVeryPainful
            NO.id -> No
            YES_JUST_A_BIT.id -> YesJustABit
            else -> throw IllegalArgumentException("Unknown physical symptoms: $src")
        }

        fun PhysicalSymptoms?.fromValue(): Int = this?.id ?: -1

        fun getAll(): List<PhysicalSymptoms> = listOf(YesVeryPainful, No, YesJustABit)
    }
}

