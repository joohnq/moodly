package com.joohnq.domain.repository

import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(id: String): Flow<User>
    suspend fun addUser(user: User): Result<Boolean>
    suspend fun hasUser(id: String): Boolean
    suspend fun updateUser(user: User): Result<Boolean>
    suspend fun updateUserName(name: String): Result<Boolean>
    suspend fun updateUserImage(image: String, imageType: ImageType): Result<Boolean>
    suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp): Result<Boolean>
    suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms): Result<Boolean>
    suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Result<Boolean>
}