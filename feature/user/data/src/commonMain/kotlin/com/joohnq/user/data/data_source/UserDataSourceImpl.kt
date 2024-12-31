package com.joohnq.user.data.data_source

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserDataSource
import com.joohnq.user.data.converter.UserConverter
import com.joohnq.user.database.user.UserDatabaseSql

class UserDataSourceImpl(private val database: UserDatabaseSql) : UserDataSource {
    private val query = database.userQueries
    override fun getUser(): User? {
        val res = query.getUser(1).executeAsOneOrNull() ?: return null
        return User(
            id = res.id.toInt(),
            name = res.name,
            medicationsSupplements = UserConverter.toMedicationsSupplements(res.medicationsSupplements),
            physicalSymptoms = UserConverter.toPhysicalSymptoms(res.physicalSymptoms),
            soughtHelp = UserConverter.toProfessionalHelp(res.soughtHelp)
        )
    }

    override suspend fun addUser(user: User): Boolean = try {
        query.addUser(
            id = user.id.toLong(),
            name = user.name,
            medicationsSupplements = UserConverter.fromMedicationsSupplements(user.medicationsSupplements),
            physicalSymptoms = UserConverter.fromPhysicalSymptoms(user.physicalSymptoms),
            soughtHelp = UserConverter.fromProfessionalHelp(user.soughtHelp)
        )
        true
    } catch (e: Exception) {
        false
    }

    override suspend fun updateUser(user: User): Boolean = try {
        query.updateUser(
            id = user.id.toLong(),
            name = user.name,
            medicationsSupplements = UserConverter.fromMedicationsSupplements(user.medicationsSupplements),
            physicalSymptoms = UserConverter.fromPhysicalSymptoms(user.physicalSymptoms),
            soughtHelp = UserConverter.fromProfessionalHelp(user.soughtHelp)
        )
        true
    } catch (e: Exception) {
        false
    }

    override suspend fun initUser(): Boolean = true

    override suspend fun updateUserName(name: String): Boolean = try {
        query.updateUserName(name)
        true
    } catch (e: Exception) {
        false
    }

    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp): Boolean = try {
        query.updateSoughtHelp(UserConverter.fromProfessionalHelp(soughtHelp))
        true
    } catch (e: Exception) {
        false
    }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms): Boolean = try {
        query.updatePhysicalSymptoms(UserConverter.fromPhysicalSymptoms(physicalSymptoms))
        true
    } catch (e: Exception) {
        false
    }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Boolean =
        try {
            query.updateMedicationsSupplements(
                UserConverter.fromMedicationsSupplements(
                    medicationsSupplements
                ),
            )
            true
        } catch (e: Exception) {
            false
        }
}