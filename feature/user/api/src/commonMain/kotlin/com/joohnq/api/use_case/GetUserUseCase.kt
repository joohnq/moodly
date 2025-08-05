package com.joohnq.api.use_case

import com.joohnq.api.entity.User
import com.joohnq.api.repository.UserRepository

class GetUserUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(): Result<User> = repository.getUser()
}