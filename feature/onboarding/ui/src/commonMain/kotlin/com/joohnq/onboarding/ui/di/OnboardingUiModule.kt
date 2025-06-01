package com.joohnq.onboarding.ui.di

import com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis.OnboardingExpressionAnalysisViewModel
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val onboardingUiModule: Module = module {
    singleOf(::OnboardingViewModel)
    singleOf(::OnboardingExpressionAnalysisViewModel)
}