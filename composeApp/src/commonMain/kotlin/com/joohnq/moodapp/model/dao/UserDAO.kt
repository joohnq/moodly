package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.joohnq.moodapp.model.DatabaseConstants
import com.joohnq.moodapp.model.entities.MedicationsSupplements
import com.joohnq.moodapp.model.entities.PhysicalSymptoms

@Dao
interface UserDAO {
    @Query("UPDATE ${DatabaseConstants.USER_DATABASE} SET ${DatabaseConstants.SOUGHT_HELP} = :soughtHelp WHERE id = :id")
    suspend fun setUserSoughtHelp(soughtHelp: Boolean, id: String = "1")

    @Query("UPDATE ${DatabaseConstants.USER_DATABASE} SET ${DatabaseConstants.PHYSICAL_SYMPTOMS} = :physicalSymptoms WHERE id = :id")
    suspend fun setUserPhysicalPain(physicalSymptoms: PhysicalSymptoms, id: String = "1")

    @Query("UPDATE ${DatabaseConstants.USER_DATABASE} SET ${DatabaseConstants.MEDICATIONS_SUPPLEMENTS} = :medicationsSupplements WHERE id = :id")
    suspend fun setUserMedicationsSupplements(medicationsSupplements: MedicationsSupplements, id: String = "1")

    @Query("UPDATE ${DatabaseConstants.USER_DATABASE} SET name = :name WHERE id = :id")
    suspend fun setUserName(name: String, id: String = "1")
}