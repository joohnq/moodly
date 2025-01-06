package com.joohnq.domain.converter

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.mapper.toInt
import com.joohnq.domain.mapper.toMedicationsSupplements
import com.joohnq.domain.mapper.toPhysicalSymptoms
import com.joohnq.domain.mapper.toProfessionalHelp

object UserConverter {
    fun fromMedicationsSupplements(value: MedicationsSupplements): Long =
        value.toInt().toLong()

    fun toMedicationsSupplements(value: Long): MedicationsSupplements =
        value.toInt().toMedicationsSupplements()

    fun fromPhysicalSymptoms(value: PhysicalSymptoms): Long = value.toInt().toLong()
    fun toPhysicalSymptoms(value: Long): PhysicalSymptoms = value.toInt().toPhysicalSymptoms()
    fun fromProfessionalHelp(value: ProfessionalHelp): Long = value.toInt().toLong()
    fun toProfessionalHelp(value: Long): ProfessionalHelp = value.toInt().toProfessionalHelp()
}