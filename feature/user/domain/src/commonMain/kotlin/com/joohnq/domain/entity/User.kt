package com.joohnq.domain.entity

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.core.ui.DatetimeProvider
import kotlinx.datetime.LocalDateTime

data class User(
    val id: Int = 1,
    val name: String = "",
    val image: ImageBitmap? = null,
    val imageType: ImageType = ImageType.DRAWABLE,
    val medicationsSupplements: MedicationsSupplements = MedicationsSupplements.ImNotTakingAny,
    val soughtHelp: ProfessionalHelp = ProfessionalHelp.No,
    val physicalSymptoms: PhysicalSymptoms = PhysicalSymptoms.No,
    val dateCreated: LocalDateTime = DatetimeProvider.getCurrentDateTime(),
)
