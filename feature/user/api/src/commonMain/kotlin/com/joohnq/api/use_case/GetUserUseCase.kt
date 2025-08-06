package com.joohnq.api.use_case

import com.joohnq.api.entity.User
import com.joohnq.api.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val repository: UserRepository,
) {
    operator fun invoke(): Flow<User?> = repository.observe()
}
