package com.joohnq.domain.use_case.user

import com.joohnq.domain.repository.UserRepository

class UpdateUserNameUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(name: String): Result<Boolean> = userRepository.updateUserName(name)
}