package com.joohnq.api.use_case

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.api.entity.ImageType
import com.joohnq.api.repository.UserRepository
import com.joohnq.storage.api.FileStorage
import com.joohnq.ui.mapper.toByteArray

class UpdateUserImageBitmapUseCase(
    private val fileStorage: FileStorage,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(image: ImageBitmap): Result<Boolean> {
        val value =
            try {
                fileStorage.saveImage(
                    directory = "avatar",
                    fileName = "avatar.png",
                    data = image.toByteArray()
                )
            } catch (e: Exception) {
                return Result.failure(e)
            }
        return userRepository.updateUserImage(
            image = value,
            imageType = ImageType.DEVICE
        )
    }
}
