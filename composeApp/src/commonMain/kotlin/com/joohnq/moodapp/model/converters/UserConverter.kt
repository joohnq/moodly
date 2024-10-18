package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.model.entities.MedicationsSupplements
import com.joohnq.moodapp.model.entities.PhysicalSymptoms

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
}