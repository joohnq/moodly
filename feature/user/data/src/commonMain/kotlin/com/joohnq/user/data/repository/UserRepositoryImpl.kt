package com.joohnq.user.data.repository

import com.joohnq.core.database.executeTryCatchPrinting
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.data_source.UserDataSource
import com.joohnq.domain.repository.UserRepository


class UserRepositoryImpl(
    private val dataSource: UserDataSource,
) : UserRepository {
    override suspend fun getUser(): User? = dataSource.getUser()

    override suspend fun addUser(user: User): Boolean =
        executeTryCatchPrinting {
            dataSource.addUser(user)
        }

    override suspend fun updateUser(user: User): Boolean =
        executeTryCatchPrinting {
            dataSource.updateUser(user)
        }

    override suspend fun initUser(): Boolean =
        executeTryCatchPrinting {
            dataSource.initUser()
        }

    override suspend fun updateUserName(name: String) =
        executeTryCatchPrinting {
            dataSource.updateUserName(name)
        }

    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp) =
        executeTryCatchPrinting {
            dataSource.updateSoughtHelp(soughtHelp)
        }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms) =
        executeTryCatchPrinting {
            dataSource.updatePhysicalSymptoms(physicalSymptoms)
        }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements) =
        executeTryCatchPrinting {
            dataSource.updateMedicationsSupplements(medicationsSupplements)
        }
}