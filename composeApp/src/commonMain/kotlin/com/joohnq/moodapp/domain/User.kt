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
    companion object {
        fun init(): User = User(
            id = 1,
            name = "",
            medicationsSupplements = MedicationsSupplements.ImNotTakingAny,
            soughtHelp = ProfessionalHelp.No,
            physicalSymptoms = PhysicalSymptoms.No,
            dateCreated = DatetimeManager.getCurrentDateTime()
        )
    }
}
