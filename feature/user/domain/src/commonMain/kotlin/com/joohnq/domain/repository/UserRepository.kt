package com.joohnq.domain.repository

import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User

interface UserRepository {
    suspend fun getUser(): Result<User>
    suspend fun addUser(user: User): Result<Boolean>
    suspend fun updateUser(user: User): Result<Boolean>
    suspend fun updateUserName(name: String): Result<Boolean>
    suspend fun updateUserImage(value: String, imageType: ImageType): Result<Boolean>
    suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp): Result<Boolean>
    suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms): Result<Boolean>
    suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Result<Boolean>
}