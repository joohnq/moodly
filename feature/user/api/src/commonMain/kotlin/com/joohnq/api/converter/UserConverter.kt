package com.joohnq.api.converter

import com.joohnq.api.entity.MedicationsSupplements
import com.joohnq.api.entity.PhysicalSymptoms
import com.joohnq.api.entity.ProfessionalHelp
import com.joohnq.api.mapper.toInt
import com.joohnq.api.mapper.toMedicationsSupplements
import com.joohnq.api.mapper.toPhysicalSymptoms
import com.joohnq.api.mapper.toProfessionalHelp

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