package com.joohnq.domain.fake

import com.joohnq.core.test.CustomFake
import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository

class UserRepositoryFake : UserRepository, CustomFake {
    override var shouldThrowError: Boolean = false
    private var item = User()

    override suspend fun getUser(): Result<User> {
        if (shouldThrowError) return Result.failure(Exception("Failed to get user"))

        return Result.success(item)
    }

    override suspend fun addUser(user: User): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to add user"))

        return Result.success(true)
    }

    override suspend fun updateUser(user: User): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to update user"))

        item = user

        return Result.success(true)
    }

    override suspend fun updateUserName(name: String): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to update user name"))

        item = item.copy(name = name)

        return Result.success(true)
    }

    override suspend fun updateUserImage(image: String, imageType: ImageType): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to update user name"))

        item = item.copy(image = image, imageType = imageType)

        return Result.success(true)
    }

    override suspend fun updateSoughtHelp(soughtHelp: ProfessionalHelp): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to update user sought help"))

        item = item.copy(soughtHelp = soughtHelp)

        return Result.success(true)
    }

    override suspend fun updatePhysicalSymptoms(physicalSymptoms: PhysicalSymptoms): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to update user physical symptoms"))

        item = item.copy(physicalSymptoms = physicalSymptoms)

        return Result.success(true)
    }

    override suspend fun updateMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to update user medications supplements"))

        item = item.copy(medicationsSupplements = medicationsSupplements)

        return Result.success(true)
    }
}