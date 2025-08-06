package com.joohnq.onboarding.impl.di

import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val onboardingImplModule: Module =
    module {
        single {
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
