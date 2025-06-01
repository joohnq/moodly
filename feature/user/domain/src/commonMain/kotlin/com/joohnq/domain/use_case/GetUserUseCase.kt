package com.joohnq.domain.use_case

import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val userRepository: UserRepository) {
    operator fun invoke(id: String): Flow<User> = userRepository.getUser(id)
}