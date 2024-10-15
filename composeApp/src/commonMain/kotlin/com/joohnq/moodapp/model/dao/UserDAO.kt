package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.joohnq.moodapp.view.entities.MedicationsSupplements
import com.joohnq.moodapp.view.entities.PhysicalSymptoms

@Dao
interface UserDAO {
    @Query("UPDATE user SET sought_help = :soughtHelp WHERE id = :id")
    suspend fun setUserSoughtHelp(soughtHelp: Boolean, id: String = "1")

    @Query("UPDATE user SET physical_symptoms = :physicalSymptoms WHERE id = :id")
    suspend fun setUserPhysicalPain(physicalSymptoms: PhysicalSymptoms, id: String = "1")

    @Query("UPDATE user SET medications_supplements = :medicationsSupplements WHERE id = :id")
    suspend fun setUserMedicationsSupplements(medicationsSupplements: MedicationsSupplements, id: String = "1")
}