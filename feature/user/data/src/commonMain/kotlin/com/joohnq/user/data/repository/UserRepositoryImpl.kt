package com.joohnq.user.data.repository

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository
import com.joohnq.user.data.dao.UserDAO

class UserRepositoryImpl(
    private val userDAO: UserDAO
) : UserRepository {
    override suspend fun getUser(): User = userDAO.getUser()

    override suspend fun addUser(user: User): Boolean =
        try {
            userDAO.addUser(user)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateUser(user: User): Boolean =
        try {
            userDAO.updateUser(user)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun initUser(): Boolean =
        try {
            userDAO.initUser()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateUserName(name: String) =
        try {
            userDAO.updateUserName(name)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateSoughtHelp(soughtHelp: Boolean) =
        try {
            userDAO.updateSoughtHelp(soughtHelp)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms) =
        try {
            userDAO.updatePhysicalSymptoms(physicalSymptoms)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements) =
        try {
            userDAO.updateMedicationsSupplements(medicationsSupplements)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
}