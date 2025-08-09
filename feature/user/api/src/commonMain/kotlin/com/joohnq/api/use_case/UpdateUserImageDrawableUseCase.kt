package com.joohnq.api.use_case

import com.joohnq.api.entity.ImageType
import com.joohnq.api.repository.UserRepository

class UpdateUserImageDrawableUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(i: Int): Result<Unit> =
        try {
            repository.updateUserImage(
                image = i.toString(),
                imageType = ImageType.DRAWABLE
            )

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
