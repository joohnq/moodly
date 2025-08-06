package com.joohnq.stress_level.overview.di

import com.joohnq.stress_level.overview.presentation.StressLevelOverviewViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val stressLevelOverviewModule: Module =
    module {
        viewModel<StressLevelOverviewViewModel> {
            StressLevelOverviewViewModel(
                getAllStressLevelUseCase = get(),
                deleteStressLevelUseCase = get()
            )
        }
    }
