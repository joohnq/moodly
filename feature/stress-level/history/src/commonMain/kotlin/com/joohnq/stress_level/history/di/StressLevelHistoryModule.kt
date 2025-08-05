package com.joohnq.stress_level.history.di

import com.joohnq.stress_level.history.presentation.StressLevelHistoryViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val stressLevelHistoryModule: Module =
    module {
        single<StressLevelHistoryViewModel> {
            StressLevelHistoryViewModel(
                getAllStressLevelUseCase = get(),
                deleteStressLevelUseCase = get()
            )
        }
    }
