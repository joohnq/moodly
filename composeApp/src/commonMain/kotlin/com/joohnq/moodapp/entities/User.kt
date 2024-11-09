package com.joohnq.moodapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.model.DatabaseConstants
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.USER_DATABASE)
@Serializable
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = DatabaseConstants.MEDICATIONS_SUPPLEMENTS)
    val medicationsSupplements: MedicationsSupplements,
    @ColumnInfo(name = DatabaseConstants.SOUGHT_HELP)
    val soughtHelp: ProfessionalHelp,
    @ColumnInfo(name = DatabaseConstants.PHYSICAL_SYMPTOMS)
    val physicalSymptoms: PhysicalSymptoms
) {
    companion object {
        fun init(): User = User(
            id = 1,
            name = "",
            medicationsSupplements = MedicationsSupplements.ImNotTakingAny,
            soughtHelp = ProfessionalHelp.No,
            physicalSymptoms = PhysicalSymptoms.No
        )
    }
}
