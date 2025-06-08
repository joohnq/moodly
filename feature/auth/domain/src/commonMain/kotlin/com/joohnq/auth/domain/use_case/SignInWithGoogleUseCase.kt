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
            println("useCase")
            val res = authRepository.signInWithGoogle(token, accessToken)

            val newUser = user.copy(id = res.id)

            if (res.isNew) {
                userRepository.addUser(newUser).getOrThrow()
            } else {
                val hasUser = userRepository.hasUser(newUser.id)
                if (!hasUser) throw AuthException.UserNotFound
            }

            Result.success(Unit)
        }
}