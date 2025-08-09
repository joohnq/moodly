package com.joohnq.api.repository

import com.joohnq.api.entity.ImageType
import com.joohnq.api.entity.MedicationsSupplements
import com.joohnq.api.entity.PhysicalSymptoms
import com.joohnq.api.entity.ProfessionalHelp
import com.joohnq.api.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun observe(): Flow<User?>

    suspend fun add(user: User)

    suspend fun update(user: User)

    suspend fun updateUserName(name: String)

    suspend fun updateUserImage(
        image: String,
        imageType: ImageType,
    )

    suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp)

    suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms)

    suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements)
}
