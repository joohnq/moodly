package com.joohnq.onboarding.ui.di

import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan
class OnboardingUiModule {
    @KoinViewModel
    fun provideOnboardingViewModel(): OnboardingViewModel = OnboardingViewModel()
}