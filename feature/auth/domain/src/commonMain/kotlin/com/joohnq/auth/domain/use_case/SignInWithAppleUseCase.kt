package com.joohnq.auth.domain.use_case

import com.joohnq.auth.domain.repository.AuthRepository
import com.joohnq.domain.entity.User

class SignInWithAppleUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: User): Result<Unit> =
        runCatching {
            authRepository.signInWithApple(token = user.id, accessToken = null)
        }
}