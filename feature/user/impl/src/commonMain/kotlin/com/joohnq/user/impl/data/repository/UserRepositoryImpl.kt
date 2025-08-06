package com.joohnq.user.impl.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.joohnq.api.converter.UserConverter
import com.joohnq.api.entity.ImageType
import com.joohnq.api.entity.MedicationsSupplements
import com.joohnq.api.entity.PhysicalSymptoms
import com.joohnq.api.entity.ProfessionalHelp
import com.joohnq.api.entity.User
import com.joohnq.api.mapper.ImageTypeMapper.toImageType
import com.joohnq.api.mapper.ImageTypeMapper.toValue
import com.joohnq.api.repository.UserRepository
import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.database.executeTryCatchResult
import com.joohnq.user.database.UserDatabaseSql
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val database: UserDatabaseSql,
) : UserRepository {
    private val query = database.userQueries

    override fun observe(): Flow<User?> =
        query
            .getUser {
                id,
                name,
                image,
                imageType,
                medicationsSupplements,
                soughtHelp,
                physicalSymptoms,
                dateCreated,
                ->
                User(
                    id = id.toInt(),
                    name = name,
                    image = image,
                    imageType = imageType.toImageType(),
                    medicationsSupplements =
                        UserConverter.toMedicationsSupplements(
                            medicationsSupplements
                        ),
                    soughtHelp = UserConverter.toProfessionalHelp(soughtHelp),
                    physicalSymptoms = UserConverter.toPhysicalSymptoms(physicalSymptoms),
                    dateCreated = LocalDateTimeConverter.toLocalDate(dateCreated)
                )
            }.asFlow()
            .mapToOneOrNull(Dispatchers.IO)

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

    override suspend fun updateUserImage(
        image: String,
        imageType: ImageType,
    ): Result<Boolean> =
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
                UserConverter.fromMedicationsSupplements(medicationsSupplements)
            )
            true
        }
}
