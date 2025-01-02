package com.joohnq.onboarding.ui.di

import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val onboardingUiModule = module {
    viewModelOf(::OnboardingViewModel)
}