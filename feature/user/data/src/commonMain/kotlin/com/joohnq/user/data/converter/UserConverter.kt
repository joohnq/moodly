package com.joohnq.user.data.converter

import androidx.room.TypeConverter
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp

class UserConverter {
    @TypeConverter
    fun fromMedicationsSupplements(value: MedicationsSupplements): Int =
        MedicationsSupplements.fromValue(value)

    @TypeConverter
    fun toMedicationsSupplements(value: Int): MedicationsSupplements =
        MedicationsSupplements.toValue(value)

    @TypeConverter
    fun fromPhysicalSymptoms(value: PhysicalSymptoms): Int = PhysicalSymptoms.fromValue(value)

    @TypeConverter
    fun toPhysicalSymptoms(value: Int): PhysicalSymptoms = PhysicalSymptoms.toValue(value)

    @TypeConverter
    fun fromProfessionalHelp(value: ProfessionalHelp): Int = ProfessionalHelp.fromValue(value)

    @TypeConverter
    fun toProfessionalHelp(value: Int): ProfessionalHelp = ProfessionalHelp.toValue(value)
}