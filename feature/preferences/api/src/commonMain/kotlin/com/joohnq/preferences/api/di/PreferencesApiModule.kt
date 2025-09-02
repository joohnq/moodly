package com.joohnq.preferences.api.di

import com.joohnq.preferences.api.use_case.GetPreferencesUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipAuthUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipOnboardingUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipSecurityUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipWelcomeUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val preferencesApiModule =
    module {
        factoryOf(::GetPreferencesUseCase)
        factoryOf(::UpdateSkipAuthUseCase)
        factoryOf(::UpdateSkipOnboardingUseCase)
        factoryOf(::UpdateSkipWelcomeUseCase)
        factoryOf(::UpdateSkipSecurityUseCase)
    }
