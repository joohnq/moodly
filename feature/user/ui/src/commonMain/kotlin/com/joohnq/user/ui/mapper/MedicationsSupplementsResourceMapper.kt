package com.joohnq.user.ui.mapper

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.user.ui.resource.MedicationsSupplementsResource
import com.joohnq.user.ui.resource.MedicationsSupplementsResource.ImNotTakingAny
import com.joohnq.user.ui.resource.MedicationsSupplementsResource.OverTheCounterSupplements
import com.joohnq.user.ui.resource.MedicationsSupplementsResource.PreferNotToSay
import com.joohnq.user.ui.resource.MedicationsSupplementsResource.PrescribedMedications

fun getAllMedicationsSupplementsResource(): List<MedicationsSupplementsResource> = listOf(
    PrescribedMedications,
    OverTheCounterSupplements,
    ImNotTakingAny,
    PreferNotToSay
)

fun MedicationsSupplements.toResource(): MedicationsSupplementsResource = when (this) {
    MedicationsSupplements.PrescribedMedications -> PrescribedMedications
    MedicationsSupplements.OverTheCounterSupplements -> OverTheCounterSupplements
    MedicationsSupplements.ImNotTakingAny -> ImNotTakingAny
    MedicationsSupplements.PreferNotToSay -> PreferNotToSay
}

fun MedicationsSupplementsResource.toDomain(): MedicationsSupplements = when (this) {
    PrescribedMedications -> MedicationsSupplements.PrescribedMedications
    OverTheCounterSupplements -> MedicationsSupplements.OverTheCounterSupplements
    ImNotTakingAny -> MedicationsSupplements.ImNotTakingAny
    PreferNotToSay -> MedicationsSupplements.PreferNotToSay
}