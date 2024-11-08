package com.joohnq.moodapp.model.repository

import com.joohnq.moodapp.entities.MedicationsSupplements
import com.joohnq.moodapp.entities.PhysicalSymptoms
import com.joohnq.moodapp.entities.User
import com.joohnq.moodapp.model.dao.UserDAO

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