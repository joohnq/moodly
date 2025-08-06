package com.joohnq.api.repository

import com.joohnq.api.entity.ImageType
import com.joohnq.api.entity.MedicationsSupplements
import com.joohnq.api.entity.PhysicalSymptoms
import com.joohnq.api.entity.ProfessionalHelp
import com.joohnq.api.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun observe(): Flow<User?>

    suspend fun addUser(user: User): Result<Boolean>

    suspend fun updateUser(user: User): Result<Boolean>

    suspend fun updateUserName(name: String): Result<Boolean>

    suspend fun updateUserImage(
        image: String,
        imageType: ImageType,
    ): Result<Boolean>

    suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp): Result<Boolean>

    suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms): Result<Boolean>

    suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Result<Boolean>
}
