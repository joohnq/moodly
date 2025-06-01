package com.joohnq.auth.domain.use_case

import com.joohnq.auth.domain.AuthException
import com.joohnq.auth.domain.repository.AuthRepository
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository

class SignInWithGoogleUseCase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User, token: String, accessToken: String?): Result<Unit> =
        runCatching {
            val isNew = authRepository.signInWithGoogle(token, accessToken)

            if (isNew) {
                userRepository.addUser(user).getOrThrow()
            } else {
                val hasUser = userRepository.hasUser(user.id)
                if (!hasUser) throw AuthException.UserNotFound
            }
            Result.success(Unit)
        }
}