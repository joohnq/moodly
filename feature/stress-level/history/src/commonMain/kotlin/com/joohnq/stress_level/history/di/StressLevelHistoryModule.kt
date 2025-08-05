package com.joohnq.stress_level.history.di

import com.joohnq.stress_level.history.presentation.StressLevelHistoryViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val stressLevelHistoryModule: Module =
    module {
        viewModel {
            StressLevelHistoryViewModel(
                getAllStressLevelUseCase = get(),
                deleteStressLevelUseCase = get()
            )
        }
    }