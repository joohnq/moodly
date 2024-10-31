package com.joohnq.moodapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joohnq.moodapp.model.DatabaseConstants
import com.joohnq.moodapp.entities.MedicationsSupplements
import com.joohnq.moodapp.entities.PhysicalSymptoms
import com.joohnq.moodapp.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Query("SELECT * FROM ${DatabaseConstants.USER_DATABASE} WHERE id = 1")
    suspend fun getUser(): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun initUser(user: User = User.init())

    @Query("UPDATE ${DatabaseConstants.USER_DATABASE} SET name = :name WHERE id = 1")
    suspend fun setUserName(name: String)

    @Query("UPDATE ${DatabaseConstants.USER_DATABASE} SET ${DatabaseConstants.SOUGHT_HELP} = :soughtHelp WHERE id = :id")
    suspend fun setUserSoughtHelp(soughtHelp: Boolean, id: String = "1")

    @Query("UPDATE ${DatabaseConstants.USER_DATABASE} SET ${DatabaseConstants.PHYSICAL_SYMPTOMS} = :physicalSymptoms WHERE id = :id")
    suspend fun setUserPhysicalPain(physicalSymptoms: PhysicalSymptoms, id: String = "1")

    @Query("UPDATE ${DatabaseConstants.USER_DATABASE} SET ${DatabaseConstants.MEDICATIONS_SUPPLEMENTS} = :medicationsSupplements WHERE id = :id")
    suspend fun setUserMedicationsSupplements(medicationsSupplements: MedicationsSupplements, id: String = "1")
}