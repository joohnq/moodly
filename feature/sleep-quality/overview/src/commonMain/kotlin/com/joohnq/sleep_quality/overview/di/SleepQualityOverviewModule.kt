package com.joohnq.sleep_quality.overview.di

import com.joohnq.sleep_quality.overview.presentation.SleepQualityOverviewViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sleepQualityOverviewModule: Module =
    module {
        viewModel {
            SleepQualityOverviewViewModel(
                getSleepQualitiesUseCase = get(),
                deleteSleepQualityUseCase = get()
            )
        }
    }