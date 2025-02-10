package com.joohnq.preferences.domain.use_case

import com.joohnq.preferences.domain.repository.PreferencesRepository

class UpdateSkipAuthUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(value: Boolean): Result<Boolean> =
        repository.updateSkipAuth(value)
}