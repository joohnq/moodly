package com.joohnq.api.use_case

import com.joohnq.api.repository.UserRepository

class UpdateUserNameUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(name: String): Result<Boolean> = userRepository.updateUserName(name)
}
