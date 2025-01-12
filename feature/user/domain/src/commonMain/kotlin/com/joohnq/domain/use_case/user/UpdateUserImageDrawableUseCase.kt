package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.repository.UserRepository

class UpdateUserImageDrawableUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(i: Int): Result<Boolean> =
        userRepository.updateUserImage(
            image = i.toString(),
            imageType = ImageType.DEVICE
        )
}