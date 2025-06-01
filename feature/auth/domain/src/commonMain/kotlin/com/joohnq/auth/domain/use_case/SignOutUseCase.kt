package com.joohnq.auth.domain.use_case

import com.joohnq.auth.domain.repository.AuthRepository

class SignOutUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return runCatching {
            repository.signOut()
        }
    }
}