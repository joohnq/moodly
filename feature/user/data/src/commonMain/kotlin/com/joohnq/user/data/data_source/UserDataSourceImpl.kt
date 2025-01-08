package com.joohnq.user.data.data_source

import com.joohnq.core.database.converters.LocalDateTimeConverter
import com.joohnq.domain.converter.UserConverter
import com.joohnq.domain.data_source.UserDataSource
import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.mapper.toImageType
import com.joohnq.domain.mapper.toValue
import com.joohnq.storage.domain.FileStorage
import com.joohnq.user.database.UserDatabaseSql
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class UserDataSourceImpl(
    private val database: UserDatabaseSql,
    private val fileStorage: FileStorage,
) : UserDataSource {
    private val query = database.userQueries
    override suspend fun getUser(): User? =
        withContext(Dispatchers.IO) {
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
                    dateCreated = LocalDateTimeConverter.toLocalDateTime(dateCreated)
                )
            }).executeAsOneOrNull()
        }

    override suspend fun addUser(user: User) =
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

    override suspend fun updateUser(user: User) =
        withContext(Dispatchers.IO) {
            query.updateUser(
                name = user.name,
                medicationsSupplements = UserConverter.fromMedicationsSupplements(user.medicationsSupplements),
                physicalSymptoms = UserConverter.fromPhysicalSymptoms(user.physicalSymptoms),
                soughtHelp = UserConverter.fromProfessionalHelp(user.soughtHelp)
            )
        }

    override suspend fun updateUserName(name: String) =
        withContext(Dispatchers.IO) {
            query.updateUserName(name)
        }

    override suspend fun updateUserImage(value: String, imageType: ImageType) =
        withContext(Dispatchers.IO) {
            query.updateUserImage(value, imageType.toValue())
        }

    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp) =
        withContext(Dispatchers.IO) {
            query.updateSoughtHelp(UserConverter.fromProfessionalHelp(soughtHelp))
        }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms) =
        withContext(Dispatchers.IO) {
            query.updatePhysicalSymptoms(UserConverter.fromPhysicalSymptoms(physicalSymptoms))
        }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements) =
        withContext(Dispatchers.IO) {
            query.updateMedicationsSupplements(
                UserConverter.fromMedicationsSupplements(
                    medicationsSupplements
                ),
            )
        }
}