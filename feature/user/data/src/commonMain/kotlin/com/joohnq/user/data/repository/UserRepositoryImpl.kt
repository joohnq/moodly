package com.joohnq.user.data.repository

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserDataSource
import com.joohnq.domain.repository.UserRepository
import org.koin.core.annotation.Single

@Single(binds = [UserRepository::class])
class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override suspend fun getUser(): User? = userDataSource.getUser()

    override suspend fun addUser(user: User): Boolean =
        try {
            userDataSource.addUser(user)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateUser(user: User): Boolean =
        try {
            userDataSource.updateUser(user)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun initUser(): Boolean =
        try {
            userDataSource.initUser()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateUserName(name: String) =
        try {
            userDataSource.updateUserName(name)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp) =
        try {
            userDataSource.updateSoughtHelp(soughtHelp)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms) =
        try {
            userDataSource.updatePhysicalSymptoms(physicalSymptoms)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements) =
        try {
            userDataSource.updateMedicationsSupplements(medicationsSupplements)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}