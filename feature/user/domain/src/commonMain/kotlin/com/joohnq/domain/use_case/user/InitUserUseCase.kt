package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository

class InitUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): Boolean = repository.initUser()
}