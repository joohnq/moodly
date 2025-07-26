package com.joohnq.api.use_case

import com.joohnq.api.entity.User
import com.joohnq.api.repository.UserRepository

class AddUserUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(user: User): Result<Boolean> = userRepository.addUser(user)
}
