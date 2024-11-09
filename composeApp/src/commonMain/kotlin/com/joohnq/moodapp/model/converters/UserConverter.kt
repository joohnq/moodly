package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.entities.MedicationsSupplements
import com.joohnq.moodapp.entities.PhysicalSymptoms
import com.joohnq.moodapp.entities.ProfessionalHelp

class UserConverter {
    @TypeConverter
    fun fromMedicationsSupplements(value: MedicationsSupplements): String =
        MedicationsSupplements.fromValue(value)

    @TypeConverter
    fun toMedicationsSupplements(value: String): MedicationsSupplements =
        MedicationsSupplements.toValue(value)

    @TypeConverter
    fun fromPhysicalSymptoms(value: PhysicalSymptoms): String = PhysicalSymptoms.fromValue(value)

    @TypeConverter
    fun toPhysicalSymptoms(value: String): PhysicalSymptoms = PhysicalSymptoms.toValue(value)

    @TypeConverter
    fun fromProfessionalHelp(value: ProfessionalHelp): String = ProfessionalHelp.fromValue(value)

    @TypeConverter
    fun toProfessionalHelp(value: String): ProfessionalHelp = ProfessionalHelp.toValue(value)
}