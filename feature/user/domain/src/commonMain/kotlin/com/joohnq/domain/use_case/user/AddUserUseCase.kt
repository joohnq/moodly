package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository

class AddUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User): Result<Boolean> = userRepository.addUser(user)
}