package com.joohnq.domain.data_source

import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User

interface UserDataSource {
    suspend fun getUser(): User?
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun updateUserName(name: String)
    suspend fun updateUserImage(value: String, imageType: ImageType)
    suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp)
    suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms)
    suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements)
}