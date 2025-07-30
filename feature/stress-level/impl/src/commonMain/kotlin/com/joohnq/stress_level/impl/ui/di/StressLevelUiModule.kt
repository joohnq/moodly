package com.joohnq.stress_level.impl.ui.di

import com.joohnq.stress_level.impl.ui.presentation.add.AddStressLevelViewModel
import com.joohnq.stress_level.impl.ui.presentation.overview.StressLevelOverviewViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val stressLevelUiModule: Module =
    module {
        single<StressLevelOverviewViewModel> {
            StressLevelOverviewViewModel(
                getAllStressLevelUseCase = get(),
                addStressLevelUseCase = get(),
                deleteStressLevelUseCase = get()
            )
        }
        single<AddStressLevelViewModel> {
            AddStressLevelViewModel()
        }
    }
