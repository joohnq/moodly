package com.joohnq.onboarding.impl.ui.di

import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val onboardingUiModule: Module =
    module {
        single<OnboardingViewModel> {
            OnboardingViewModel(
                addMoodUseCase = get(),
                addStressLevelUseCase = get(),
                addSleepQualityUseCase = get(),
                updatePhysicalSymptomsUseCase = get(),
                updateSoughtHelpUseCase = get(),
                updateMedicationsSupplementsUseCase = get(),
                updateSkipOnboardingUseCase = get()
            )
        }
    }
