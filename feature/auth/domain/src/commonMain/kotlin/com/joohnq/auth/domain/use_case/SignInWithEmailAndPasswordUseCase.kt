package com.joohnq.auth.domain.use_case

import com.joohnq.auth.domain.repository.AuthRepository
import com.joohnq.domain.validation.EmailValidator
import com.joohnq.domain.validation.PasswordValidator

class SignInWithEmailAndPasswordUseCase(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> = runCatching {
        emailValidator.validate(email)
        passwordValidator.validate(password)

        repository.signInWithEmailAndPassword(email = email, password = password)
    }
}