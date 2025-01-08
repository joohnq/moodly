package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.repository.UserRepository
import org.jetbrains.compose.resources.DrawableResource

class UpdateUserImageDrawableUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(drawable: DrawableResource): Result<Boolean> {
        val value = drawable.toString()

        return userRepository.updateUserImage(
            value = value,
            imageType = ImageType.DEVICE
        )
    }
}