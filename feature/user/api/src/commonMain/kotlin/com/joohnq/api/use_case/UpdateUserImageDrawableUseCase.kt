package com.joohnq.api.use_case

import com.joohnq.api.entity.ImageType
import com.joohnq.api.repository.UserRepository

class UpdateUserImageDrawableUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(i: Int): Result<Boolean> =
        repository.updateUserImage(
            image = i.toString(),
            imageType = ImageType.DRAWABLE
        )
}