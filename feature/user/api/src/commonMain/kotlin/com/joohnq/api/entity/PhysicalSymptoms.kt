package com.joohnq.api.entity

import com.joohnq.api.property.PhysicalSymptomsProperties

sealed class PhysicalSymptoms(
    override val id: Int
) : PhysicalSymptomsProperties {
    data object YesVeryPainful : PhysicalSymptoms(YES_VERY_PAINFUL.id)

    data object No : PhysicalSymptoms(NO.id)

    data object YesJustABit : PhysicalSymptoms(YES_JUST_A_BIT.id)

    companion object {
        val YES_VERY_PAINFUL = DPhysicalSymptomsProperties(0)
        val NO = DPhysicalSymptomsProperties(1)
        val YES_JUST_A_BIT = DPhysicalSymptomsProperties(2)
    }
}
