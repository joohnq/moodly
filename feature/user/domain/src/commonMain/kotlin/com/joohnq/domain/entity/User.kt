package com.joohnq.domain.entity

import com.joohnq.shared.domain.DatetimeProvider
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int = 1,
    val name: String = "",
    val medicationsSupplements: MedicationsSupplements = MedicationsSupplements.ImNotTakingAny,
    val soughtHelp: ProfessionalHelp = ProfessionalHelp.No,
    val physicalSymptoms: PhysicalSymptoms = PhysicalSymptoms.No,
    val dateCreated: LocalDateTime = DatetimeProvider.getCurrentDateTime(),
) {
    companion object {
        fun init(): User = User()
    }
}
