package com.joohnq.api.use_case

import com.joohnq.api.repository.UserRepository

class UpdateUserNameUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(name: String): Result<Unit> =
        try {
            repository.updateUserName(name)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
}
