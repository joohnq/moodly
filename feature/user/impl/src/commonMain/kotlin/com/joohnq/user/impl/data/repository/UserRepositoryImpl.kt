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
import com.joohnq.user.database.UserDatabaseSql
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

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

    override suspend fun add(user: User) {
        withContext(Dispatchers.IO) {
            query.addUser(
                id = user.id.toLong(),
                name = user.name,
                image = user.image,
                imageType = user.imageType.name,
                medicationsSupplements = UserConverter.fromMedicationsSupplements(user.medicationsSupplements),
                physicalSymptoms = UserConverter.fromPhysicalSymptoms(user.physicalSymptoms),
                soughtHelp = UserConverter.fromProfessionalHelp(user.soughtHelp)
            )
        }
    }

    override suspend fun update(user: User) {
        withContext(Dispatchers.IO) {
            query.updateUser(
                name = user.name,
                medicationsSupplements = UserConverter.fromMedicationsSupplements(user.medicationsSupplements),
                physicalSymptoms = UserConverter.fromPhysicalSymptoms(user.physicalSymptoms),
                soughtHelp = UserConverter.fromProfessionalHelp(user.soughtHelp)
            )
        }
    }

    override suspend fun updateUserName(name: String) {
        withContext(Dispatchers.IO) {
            query.updateUserName(name)
        }
    }

    override suspend fun updateUserImage(
        image: String,
        imageType: ImageType,
    ) {
        withContext(Dispatchers.IO) {
            query.updateUserImage(image, imageType.toValue())
        }
    }

    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp) {
        withContext(Dispatchers.IO) {
            query.updateSoughtHelp(UserConverter.fromProfessionalHelp(soughtHelp))
        }
    }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms) {
        withContext(Dispatchers.IO) {
            query.updatePhysicalSymptoms(UserConverter.fromPhysicalSymptoms(physicalSymptoms))
        }
    }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements) {
        withContext(Dispatchers.IO) {
            query.updateMedicationsSupplements(
                UserConverter.fromMedicationsSupplements(medicationsSupplements)
            )
        }
    }
}
