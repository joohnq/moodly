package com.joohnq.moodapp.view.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserConverters {
    @TypeConverter
    fun fromMedicationsSupplements(value: MedicationsSupplements): String {
        return value.id
    }


    @TypeConverter
    fun toMedicationsSupplements(value: String): MedicationsSupplements {
        return MedicationsSupplements.valueOf(value)
    }

    @TypeConverter
    fun fromPhysicalSymptoms(value: PhysicalSymptoms): String {
        return value.id
    }


    @TypeConverter
    fun toPhysicalSymptoms(value: String): PhysicalSymptoms {
        return PhysicalSymptoms.valueOf(value)
    }
}

@Entity(tableName = "user")
@TypeConverters(UserConverters::class)
@Serializable
data class User(
    @PrimaryKey val id: String = "1",
    val name: String,
    @ColumnInfo(name = "medications_supplements") val medicationsSupplements: MedicationsSupplements,
    @ColumnInfo(name = "sought_help") val soughtHelp: Boolean,
    @ColumnInfo(name = "physical_symptoms") val physicalSymptoms: PhysicalSymptoms
)