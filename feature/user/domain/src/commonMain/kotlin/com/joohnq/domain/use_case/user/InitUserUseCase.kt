package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository

class InitUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): Result<Boolean> = userRepository.addUser(User())
}