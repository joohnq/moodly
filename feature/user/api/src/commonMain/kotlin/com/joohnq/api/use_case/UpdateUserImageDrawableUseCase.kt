package com.joohnq.api.use_case

import com.joohnq.api.entity.ImageType
import com.joohnq.api.repository.UserRepository

class UpdateUserImageDrawableUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(i: Int): Result<Boolean> =
        userRepository.updateUserImage(
            image = i.toString(),
            imageType = ImageType.DRAWABLE
        )
}