package com.joohnq.domain.entity

import com.joohnq.core.ui.DatetimeProvider
import kotlinx.datetime.LocalDate

data class User(
    val id: Int = 1,
    val name: String = "",
    val image: String? = null,
    val imageType: ImageType = ImageType.DRAWABLE,
    val medicationsSupplements: MedicationsSupplements = MedicationsSupplements.ImNotTakingAny,
    val soughtHelp: ProfessionalHelp = ProfessionalHelp.No,
    val physicalSymptoms: PhysicalSymptoms = PhysicalSymptoms.No,
    val dateCreated: LocalDate = DatetimeProvider.getCurrentDateTime().date,
)
