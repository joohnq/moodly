package com.joohnq.preferences.domain.di

import com.joohnq.preferences.domain.use_case.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val preferencesDomainModule = module {
    factoryOf(::GetUserPreferencesUseCase)
    factoryOf(::UpdateSkipAuthUseCase)
    factoryOf(::UpdateSkipOnboardingUseCase)
    factoryOf(::UpdateSkipWelcomeUseCase)
    factoryOf(::UpdateSkipSecurityUseCase)
}