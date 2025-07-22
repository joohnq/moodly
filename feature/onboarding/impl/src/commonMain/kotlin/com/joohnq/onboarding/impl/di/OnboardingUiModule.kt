package com.joohnq.onboarding.impl.di

import com.joohnq.onboarding.impl.viewmodel.OnboardingViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val onboardingUiModule: Module = module {
    singleOf(::OnboardingViewModel)
}