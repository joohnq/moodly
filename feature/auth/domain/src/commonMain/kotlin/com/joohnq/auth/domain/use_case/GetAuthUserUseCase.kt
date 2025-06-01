package com.joohnq.auth.domain.use_case

import com.joohnq.auth.domain.entity.AuthUser
import com.joohnq.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class GetAuthUserUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(): Flow<AuthUser?> = authRepository.observeAuthState()
}