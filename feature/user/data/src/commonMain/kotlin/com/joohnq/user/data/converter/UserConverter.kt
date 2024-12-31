package com.joohnq.user.data.converter

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.MedicationsSupplements.Companion.fromValue
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.PhysicalSymptoms.Companion.fromValue
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.ProfessionalHelp.Companion.fromValue

object UserConverter {
    fun fromMedicationsSupplements(value: MedicationsSupplements): Long =
        value.fromValue().toLong()

    fun toMedicationsSupplements(value: Long): MedicationsSupplements =
        MedicationsSupplements.toValue(value.toInt())

    fun fromPhysicalSymptoms(value: PhysicalSymptoms): Long = value.fromValue().toLong()
    fun toPhysicalSymptoms(value: Long): PhysicalSymptoms = PhysicalSymptoms.toValue(value.toInt())
    fun fromProfessionalHelp(value: ProfessionalHelp): Long = value.fromValue().toLong()
    fun toProfessionalHelp(value: Long): ProfessionalHelp = ProfessionalHelp.toValue(value.toInt())
}