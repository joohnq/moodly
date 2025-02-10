package com.joohnq.domain.use_case

import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): Result<User> = userRepository.getUser()
}