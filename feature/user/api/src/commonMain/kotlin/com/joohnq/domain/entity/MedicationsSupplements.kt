package com.joohnq.domain.entity

import com.joohnq.domain.property.MedicationsSupplementsProperties

sealed class MedicationsSupplements(
    override val id: Int,
) : MedicationsSupplementsProperties {
    data object PrescribedMedications : MedicationsSupplements(PRESCRIBED_MEDICATIONS.id)
    data object OverTheCounterSupplements : MedicationsSupplements(OVER_THE_COUNTER_SUPPLEMENTS.id)
    data object ImNotTakingAny : MedicationsSupplements(IM_NOT_TAKING_ANY.id)
    data object PreferNotToSay : MedicationsSupplements(PREFER_NOT_TO_SAY.id)

    companion object {
        val PRESCRIBED_MEDICATIONS = DMedicationsSupplementsProperties(0)
        val OVER_THE_COUNTER_SUPPLEMENTS = DMedicationsSupplementsProperties(1)
        val IM_NOT_TAKING_ANY = DMedicationsSupplementsProperties(2)
        val PREFER_NOT_TO_SAY = DMedicationsSupplementsProperties(3)
    }
}

