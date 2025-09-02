package com.joohnq.api.entity

import com.joohnq.api.getNow
import kotlinx.datetime.LocalDate

data class User(
    val id: Long = 1,
    val name: String = "",
    val image: String? = null,
    val imageType: ImageType = ImageType.DRAWABLE,
    val medicationsSupplements: MedicationsSupplements = MedicationsSupplements.ImNotTakingAny,
    val soughtHelp: ProfessionalHelp = ProfessionalHelp.No,
    val physicalSymptoms: PhysicalSymptoms = PhysicalSymptoms.No,
    val createdAt: LocalDate = getNow().date,
)
