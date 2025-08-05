package com.joohnq.api.use_case

import com.joohnq.api.entity.User
import com.joohnq.api.repository.UserRepository

class UpdateUserUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(user: User): Result<Boolean> = repository.updateUser(user)
}
