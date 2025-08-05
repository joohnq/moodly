package com.joohnq.stress_level.overview.di

import com.joohnq.stress_level.overview.presentation.StressLevelOverviewViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val stressLevelOverviewModule: Module =
    module {
        single<StressLevelOverviewViewModel> {
            StressLevelOverviewViewModel(
                getAllStressLevelUseCase = get(),
                addStressLevelUseCase = get(),
                deleteStressLevelUseCase = get()
            )
        }
    }
