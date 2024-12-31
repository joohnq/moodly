package com.joohnq.domain.use_case.user

import com.joohnq.domain.repository.UserRepository
import org.koin.core.annotation.Factory

@Factory
class UpdateUserNameUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(name: String): Boolean = userRepository.updateUserName(name)
}