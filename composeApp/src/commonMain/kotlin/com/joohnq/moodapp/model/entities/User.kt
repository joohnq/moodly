package com.joohnq.moodapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.model.DatabaseConstants
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.USER_DATABASE)
@Serializable
data class User(
    @PrimaryKey val id: Int = 1,
    val name: String = "",
    @ColumnInfo(name = DatabaseConstants.MEDICATIONS_SUPPLEMENTS) val medicationsSupplements: MedicationsSupplements = MedicationsSupplements.ImNotTakingAny,
    @ColumnInfo(name = DatabaseConstants.SOUGHT_HELP) val soughtHelp: Boolean = false,
    @ColumnInfo(name = DatabaseConstants.PHYSICAL_SYMPTOMS) val physicalSymptoms: PhysicalSymptoms = PhysicalSymptoms.No
)

fun mockUser(): User = User(
    name = "Henrique",
    medicationsSupplements = MedicationsSupplements.PrescribedMedications,
    soughtHelp = true,
    physicalSymptoms = PhysicalSymptoms.No,
)
