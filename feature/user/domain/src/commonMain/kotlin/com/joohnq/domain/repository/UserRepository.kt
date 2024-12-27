package com.joohnq.domain.repository

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.User

interface UserRepository {
    suspend fun getUser(): User
    suspend fun addUser(user: User): Boolean
    suspend fun updateUser(user: User): Boolean
    suspend fun initUser(): Boolean
    suspend fun updateUserName(name: String): Boolean
    suspend fun updateSoughtHelp(soughtHelp: Boolean): Boolean
    suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms): Boolean
    suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Boolean
}