package com.joohnq.user.impl.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.joohnq.api.entity.ImageType
import com.joohnq.api.entity.MedicationsSupplements
import com.joohnq.api.entity.PhysicalSymptoms
import com.joohnq.api.entity.ProfessionalHelp
import com.joohnq.api.entity.User
import com.joohnq.api.mapper.ImageTypeMapper.toImageType
import com.joohnq.api.mapper.MedicationsSupplementsMapper.toMedicationsSupplements
import com.joohnq.api.mapper.PhysicalSymptomsMapper.toPhysicalSymptoms
import com.joohnq.api.mapper.ProfessionalHelpMapper.toProfessionalHelp
import com.joohnq.api.repository.UserRepository
import com.joohnq.database.AppDatabaseSql
import com.joohnq.database.mapper.LocalDateMapper.toLocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val database: AppDatabaseSql,
) : UserRepository {
    private val query = database.usersQueries

    override fun observe(): Flow<User?> =
        query
            .get(userMapper)
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)

    override suspend fun add(user: User) {
        withContext(Dispatchers.IO) {
            query.add(
                id = user.id,
                name = user.name,
                image = user.image,
                imageType = user.imageType.name,
                medicationsSupplements = user.medicationsSupplements.id,
                physicalSymptoms = user.physicalSymptoms.id,
                soughtHelp = user.soughtHelp.id
            )
        }
    }

    override suspend fun update(user: User) {
        withContext(Dispatchers.IO) {
            query.update(
                name = user.name,
                image = user.image,
                imageType = user.imageType.name,
                medicationsSupplements = user.medicationsSupplements.id,
                physicalSymptoms = user.physicalSymptoms.id,
                soughtProfessionalHelp = user.soughtHelp.id,
                createdAt = user.createdAt.toString()
            )
        }
    }

    override suspend fun updateUserName(name: String) {
        withContext(Dispatchers.IO) {
            query.updateName(name)
        }
    }

    override suspend fun updateUserImage(
        image: String,
        imageType: ImageType,
    ) {
        withContext(Dispatchers.IO) {
            query.updateImage(image, imageType.toString())
        }
    }

    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp) {
        withContext(Dispatchers.IO) {
            query.updateSoughtProfessionalHelp(soughtHelp.id)
        }
    }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms) {
        withContext(Dispatchers.IO) {
            query.updatePhysicalSymptoms(physicalSymptoms.id)
        }
    }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements) {
        withContext(Dispatchers.IO) {
            query.updateMedicationsSupplements(
                medicationsSupplements.id
            )
        }
    }
}

val userMapper: (Long, String, String?, String, Long, Long, Long, String) -> User =
    { id, name, image, imageType, medicationsSupplements, soughtHelp, physicalSymptoms, dateCreated ->
        User(
            id = id,
            name = name,
            image = image,
            imageType = imageType.toImageType(),
            medicationsSupplements = medicationsSupplements.toMedicationsSupplements(),
            soughtHelp = soughtHelp.toProfessionalHelp(),
            physicalSymptoms = physicalSymptoms.toPhysicalSymptoms(),
            createdAt = dateCreated.toLocalDate()
        )
    }
