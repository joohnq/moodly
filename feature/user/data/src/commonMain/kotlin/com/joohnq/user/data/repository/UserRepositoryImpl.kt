package com.joohnq.user.data.repository

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserDataSource
import com.joohnq.domain.repository.UserRepository


class UserRepositoryImpl(
    private val dataSource: UserDataSource,
) : UserRepository {
    override suspend fun getUser(): User? = dataSource.getUser()

    override suspend fun addUser(user: User): Boolean =
        try {
            dataSource.addUser(user)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateUser(user: User): Boolean =
        try {
            dataSource.updateUser(user)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun initUser(): Boolean =
        try {
            dataSource.initUser()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateUserName(name: String) =
        try {
            dataSource.updateUserName(name)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp) =
        try {
            dataSource.updateSoughtHelp(soughtHelp)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms) =
        try {
            dataSource.updatePhysicalSymptoms(physicalSymptoms)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements) =
        try {
            dataSource.updateMedicationsSupplements(medicationsSupplements)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}