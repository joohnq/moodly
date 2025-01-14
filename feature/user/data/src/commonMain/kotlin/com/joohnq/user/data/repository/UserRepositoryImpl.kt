package com.joohnq.user.data.repository

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.core.database.executeTryCatchResult
import com.joohnq.domain.converter.UserConverter
import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.mapper.toImageType
import com.joohnq.domain.mapper.toValue
import com.joohnq.domain.repository.UserRepository
import com.joohnq.user.database.UserDatabaseSql

class UserRepositoryImpl(
    private val database: UserDatabaseSql,
) : UserRepository {
    private val query = database.userQueries
    override suspend fun getUser(): Result<User> =
        executeTryCatchResult {
            query.getUser(mapper = { id, name, image, imageType, medicationsSupplements, soughtHelp, physicalSymptoms, dateCreated ->
                User(
                    id = id.toInt(),
                    name = name,
                    image = image,
                    imageType = imageType.toImageType(),
                    medicationsSupplements = UserConverter.toMedicationsSupplements(
                        medicationsSupplements
                    ),
                    soughtHelp = UserConverter.toProfessionalHelp(soughtHelp),
                    physicalSymptoms = UserConverter.toPhysicalSymptoms(physicalSymptoms),
                    dateCreated = LocalDateTimeConverter.toLocalDate(dateCreated)
                )
            }).executeAsOneOrNull() ?: throw Exception("User not found")
        }

    override suspend fun addUser(user: User): Result<Boolean> =
        executeTryCatchResult {
            query.addUser(
                id = user.id.toLong(),
                name = user.name,
                image = user.image,
                imageType = user.imageType.name,
                medicationsSupplements = UserConverter.fromMedicationsSupplements(user.medicationsSupplements),
                physicalSymptoms = UserConverter.fromPhysicalSymptoms(user.physicalSymptoms),
                soughtHelp = UserConverter.fromProfessionalHelp(user.soughtHelp)
            )
            true
        }

    override suspend fun updateUser(user: User): Result<Boolean> =
        executeTryCatchResult {
            query.updateUser(
                name = user.name,
                medicationsSupplements = UserConverter.fromMedicationsSupplements(user.medicationsSupplements),
                physicalSymptoms = UserConverter.fromPhysicalSymptoms(user.physicalSymptoms),
                soughtHelp = UserConverter.fromProfessionalHelp(user.soughtHelp)
            )
            true
        }

    override suspend fun updateUserName(name: String): Result<Boolean> =
        executeTryCatchResult {
            query.updateUserName(name)
            true
        }

    override suspend fun updateUserImage(image: String, imageType: ImageType): Result<Boolean> =
        executeTryCatchResult {
            query.updateUserImage(image, imageType.toValue())
            true
        }

    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp): Result<Boolean> =
        executeTryCatchResult {
            query.updateSoughtHelp(UserConverter.fromProfessionalHelp(soughtHelp))
            true
        }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms): Result<Boolean> =
        executeTryCatchResult {
            query.updatePhysicalSymptoms(UserConverter.fromPhysicalSymptoms(physicalSymptoms))
            true
        }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Result<Boolean> =
        executeTryCatchResult {
            query.updateMedicationsSupplements(
                UserConverter.fromMedicationsSupplements(medicationsSupplements),
            )
            true
        }
}