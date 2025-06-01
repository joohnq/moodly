package com.joohnq.domain.entity

import com.joohnq.domain.property.MedicationsSupplementsPropertiesInterface
import kotlinx.serialization.Serializable

@Serializable
sealed class MedicationsSupplements(
    override val id: Int,
) : MedicationsSupplementsPropertiesInterface {
    data object PrescribedMedications : MedicationsSupplements(PRESCRIBED_MEDICATIONS.id)
    data object OverTheCounterSupplements : MedicationsSupplements(OVER_THE_COUNTER_SUPPLEMENTS.id)
    data object ImNotTakingAny : MedicationsSupplements(IM_NOT_TAKING_ANY.id)
    data object PreferNotToSay : MedicationsSupplements(PREFER_NOT_TO_SAY.id)

    companion object {
        val PRESCRIBED_MEDICATIONS = MedicationsSupplementsProperties(0)
        val OVER_THE_COUNTER_SUPPLEMENTS = MedicationsSupplementsProperties(1)
        val IM_NOT_TAKING_ANY = MedicationsSupplementsProperties(2)
        val PREFER_NOT_TO_SAY = MedicationsSupplementsProperties(3)

        fun from(id: Int): MedicationsSupplements {
            return when (id) {
                PRESCRIBED_MEDICATIONS.id -> PrescribedMedications
                OVER_THE_COUNTER_SUPPLEMENTS.id -> OverTheCounterSupplements
                IM_NOT_TAKING_ANY.id -> ImNotTakingAny
                PREFER_NOT_TO_SAY.id -> PreferNotToSay
                else -> throw IllegalArgumentException("Unknown id: $id")
            }
        }
    }
}
