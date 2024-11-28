package com.joohnq.moodapp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.data.DatabaseConstants
import com.joohnq.moodapp.util.helper.DatetimeManager
import kotlinx.datetime.LocalDateTime
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
    val physicalSymptoms: PhysicalSymptoms,
    @ColumnInfo(name = DatabaseConstants.DATE_CREATED) val dateCreated: LocalDateTime
) {
    class Builder {
        private var id: Int = 1
        private var name: String = ""
        private var medicationsSupplements: MedicationsSupplements =
            MedicationsSupplements.ImNotTakingAny
        private var soughtHelp: ProfessionalHelp = ProfessionalHelp.No
        private var physicalSymptoms: PhysicalSymptoms = PhysicalSymptoms.No
        private var dateCreated: LocalDateTime = DatetimeManager.getCurrentDateTime()

        fun setName(name: String) = apply { this.name = name }
        fun setMedicationsSupplements(medicationsSupplements: MedicationsSupplements) =
            apply { this.medicationsSupplements = medicationsSupplements }

        fun setSoughtHelp(soughtHelp: ProfessionalHelp) = apply { this.soughtHelp = soughtHelp }
        fun setPhysicalSymptoms(physicalSymptoms: PhysicalSymptoms) =
            apply { this.physicalSymptoms = physicalSymptoms }

        fun build() = User(
            id = id,
            name = name,
            medicationsSupplements = medicationsSupplements,
            soughtHelp = soughtHelp,
            physicalSymptoms = physicalSymptoms,
            dateCreated = dateCreated
        )
    }

    companion object {
        fun init(): User = Builder().build()
    }
}
