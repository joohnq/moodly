package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository

class UpdateUserNameUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(name: String): Boolean = repository.updateUserName(name)
}