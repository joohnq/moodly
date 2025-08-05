package com.joohnq.api.use_case

import com.joohnq.api.repository.UserRepository

class UpdateUserNameUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(name: String): Result<Boolean> = repository.updateUserName(name)
}