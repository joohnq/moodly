package com.joohnq.user.data.repository

import com.joohnq.core.database.executeTryCatchPrinting
import com.joohnq.domain.data_source.UserDataSource
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository
import com.joohnq.shared.domain.toResultNull

class UserRepositoryImpl(
    private val dataSource: UserDataSource,
) : UserRepository {
    override suspend fun getUser(): Result<User> =
        dataSource.getUser().toResultNull("User not found")

    override suspend fun addUser(user: User): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.addUser(user)
        }

    override suspend fun updateUser(user: User): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.updateUser(user)
        }

    override suspend fun updateUserName(name: String): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.updateUserName(name)
        }

    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.updateSoughtHelp(soughtHelp)
        }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.updatePhysicalSymptoms(physicalSymptoms)
        }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Result<Boolean> =
        executeTryCatchPrinting {
            dataSource.updateMedicationsSupplements(medicationsSupplements)
        }
}