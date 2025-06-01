package com.joohnq.auth.domain.use_case

import com.joohnq.auth.domain.repository.AuthRepository
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository
import com.joohnq.domain.validation.EmailValidator
import com.joohnq.domain.validation.NameValidator
import com.joohnq.domain.validation.PasswordValidator

class SignUpWithEmailAndPasswordUseCase(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val nameValidator: NameValidator,
    private val repository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String): Result<Unit> =
        runCatching {
            emailValidator.validate(email)
            passwordValidator.validate(password)
            nameValidator.validate(name)


            val id = repository.signUpWithEmailAndPassword(email = email, password = password)

            val user = User(id = id, name = name, email = email)

            userRepository.addUser(user)
                .onSuccess {
                    return Result.success(Unit)
                }
                .onFailure { error ->
                    return Result.failure(error)
                }
        }
}