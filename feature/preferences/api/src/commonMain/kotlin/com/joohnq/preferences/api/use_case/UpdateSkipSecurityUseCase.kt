package com.joohnq.preferences.api.use_case

import com.joohnq.preferences.api.repository.PreferencesRepository

class UpdateSkipSecurityUseCase(
    private val repository: PreferencesRepository,
) {
    suspend operator fun invoke(value: Boolean): Result<Boolean> = repository.updateSkipSecurity(value)
}
