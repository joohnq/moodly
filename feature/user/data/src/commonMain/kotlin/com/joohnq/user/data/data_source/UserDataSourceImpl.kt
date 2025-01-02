package com.joohnq.user.data.data_source

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.core.database.executeTryCatch
import com.joohnq.domain.converter.UserConverter
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.data_source.UserDataSource
import com.joohnq.user.database.UserDatabaseSql

class UserDataSourceImpl(private val database: UserDatabaseSql) : UserDataSource {
    private val query = database.userQueries
    override fun getUser(): User? =
        query.getUser(mapper = { id, name, medicationsSupplements, soughtHelp, physicalSymptoms, dateCreated ->
            User(
                id = id.toInt(),
                name = name,
                medicationsSupplements = UserConverter.toMedicationsSupplements(
                    medicationsSupplements
                ),
                soughtHelp = UserConverter.toProfessionalHelp(soughtHelp),
                physicalSymptoms = UserConverter.toPhysicalSymptoms(physicalSymptoms),
                dateCreated = LocalDateTimeConverter.toLocalDateTime(dateCreated)
            )
        }).executeAsOneOrNull()

    override suspend fun addUser(user: User): Boolean =
        executeTryCatch {
            query.addUser(
                id = user.id.toLong(),
                name = user.name,
                medicationsSupplements = UserConverter.fromMedicationsSupplements(user.medicationsSupplements),
                physicalSymptoms = UserConverter.fromPhysicalSymptoms(user.physicalSymptoms),
                soughtHelp = UserConverter.fromProfessionalHelp(user.soughtHelp)
            )
        }

    override suspend fun updateUser(user: User): Boolean =
        executeTryCatch {
            query.updateUser(
                name = user.name,
                medicationsSupplements = UserConverter.fromMedicationsSupplements(user.medicationsSupplements),
                physicalSymptoms = UserConverter.fromPhysicalSymptoms(user.physicalSymptoms),
                soughtHelp = UserConverter.fromProfessionalHelp(user.soughtHelp)
            )
        }

    override suspend fun initUser(): Boolean = true

    override suspend fun updateUserName(name: String): Boolean =
        executeTryCatch {
            query.updateUserName(name)
        }


    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp): Boolean =
        executeTryCatch {
            query.updateSoughtHelp(UserConverter.fromProfessionalHelp(soughtHelp))
        }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms): Boolean =
        executeTryCatch {
            query.updatePhysicalSymptoms(UserConverter.fromPhysicalSymptoms(physicalSymptoms))
        }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Boolean =
        executeTryCatch {
            query.updateMedicationsSupplements(
                UserConverter.fromMedicationsSupplements(
                    medicationsSupplements
                ),
            )
        }
}