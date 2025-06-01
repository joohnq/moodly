package com.joohnq.user.data.repository

import com.joohnq.database.executeTryCatch
import com.joohnq.database.executeTryCatchResult
import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val firestore: FirebaseFirestore,
) : UserRepository {
    val collection = firestore.collection("users")

    override fun getUser(id: String): Flow<User> =
        collection.document(id)
            .snapshots.map { snapshot ->
                snapshot.data<User>()
            }

    override suspend fun addUser(user: User): Result<Boolean> =
        executeTryCatchResult {
            collection
                .document(user.id)
                .set(user)

            true
        }

    override suspend fun hasUser(id: String): Boolean = executeTryCatch {
        val document = collection.document(id).snapshots.first()
        document.exists
    }

    override suspend fun updateUser(user: User): Result<Boolean> =
        executeTryCatchResult {
            collection
                .document(user.id)
                .set(user)

            true
        }

    override suspend fun updateUserName(name: String): Result<Boolean> =
        executeTryCatchResult {
            true
        }

    override suspend fun updateUserImage(image: String, imageType: ImageType): Result<Boolean> =
        executeTryCatchResult {

            true
        }

    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp): Result<Boolean> =
        executeTryCatchResult {
            true
        }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms): Result<Boolean> =
        executeTryCatchResult {
            true
        }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Result<Boolean> =
        executeTryCatchResult {
            true
        }
}