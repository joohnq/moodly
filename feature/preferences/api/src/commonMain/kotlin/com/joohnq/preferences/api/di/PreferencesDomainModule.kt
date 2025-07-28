package com.joohnq.preferences.api.di

import com.joohnq.preferences.api.use_case.GetUserPreferencesUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipAuthUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipOnboardingUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipSecurityUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipWelcomeUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val preferencesDomainModule =
    module {
        factoryOf(::GetUserPreferencesUseCase)
        factoryOf(::UpdateSkipAuthUseCase)
        factoryOf(::UpdateSkipOnboardingUseCase)
        factoryOf(::UpdateSkipWelcomeUseCase)
        factoryOf(::UpdateSkipSecurityUseCase)
    }
