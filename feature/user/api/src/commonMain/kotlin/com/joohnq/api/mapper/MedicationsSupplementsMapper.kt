package com.joohnq.api.mapper

import com.joohnq.api.entity.MedicationsSupplements
import com.joohnq.api.entity.MedicationsSupplements.Companion.IM_NOT_TAKING_ANY
import com.joohnq.api.entity.MedicationsSupplements.Companion.OVER_THE_COUNTER_SUPPLEMENTS
import com.joohnq.api.entity.MedicationsSupplements.Companion.PREFER_NOT_TO_SAY
import com.joohnq.api.entity.MedicationsSupplements.Companion.PRESCRIBED_MEDICATIONS
import com.joohnq.api.entity.MedicationsSupplements.ImNotTakingAny
import com.joohnq.api.entity.MedicationsSupplements.OverTheCounterSupplements
import com.joohnq.api.entity.MedicationsSupplements.PreferNotToSay
import com.joohnq.api.entity.MedicationsSupplements.PrescribedMedications

fun Int.toMedicationsSupplements(): MedicationsSupplements = when (this) {
    PRESCRIBED_MEDICATIONS.id -> PrescribedMedications
    OVER_THE_COUNTER_SUPPLEMENTS.id -> OverTheCounterSupplements
    IM_NOT_TAKING_ANY.id -> ImNotTakingAny
    PREFER_NOT_TO_SAY.id -> PreferNotToSay
    else -> throw IllegalArgumentException("Unknown medications supplements option: $this")
}

fun MedicationsSupplements?.toInt(): Int = this?.id ?: -1

fun getAllMedicationsSupplements(): List<MedicationsSupplements> = listOf(
    PrescribedMedications,
    OverTheCounterSupplements,
    ImNotTakingAny,
    PreferNotToSay
)