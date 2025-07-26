package com.joohnq.stress_level.impl.ui.di

import com.joohnq.stress_level.impl.ui.presentation.add_stress_level.AddStressLevelViewModel
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val stressLevelUiModule: Module =
    module {
        single<StressLevelViewModel> {
            StressLevelViewModel(
                getStressLevelsUseCase = get(),
                addStressLevelUseCase = get(),
                deleteStressLevelUseCase = get()
            )
        }
        single<AddStressLevelViewModel> {
            AddStressLevelViewModel()
        }
    }
