package com.joohnq.sleep_quality.impl.ui.di

import com.joohnq.sleep_quality.impl.ui.presentation.add.AddSleepQualityViewModel
import com.joohnq.sleep_quality.impl.ui.presentation.history.SleepQualityHistoryViewModel
import com.joohnq.sleep_quality.impl.ui.presentation.overview.SleepQualityOverviewViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val sleepQualityUiModule: Module =
    module {
        single<SleepQualityOverviewViewModel> {
            SleepQualityOverviewViewModel(
                addSleepQualityUseCase = get(),
                getSleepQualitiesUseCase = get(),
                deleteSleepQualityUseCase = get()
            )
        }
        single<AddSleepQualityViewModel> {
            AddSleepQualityViewModel(
                addSleepQualityUseCase = get(),
            )
        }
        single<SleepQualityHistoryViewModel> {
            SleepQualityHistoryViewModel(
                deleteSleepQualityUseCase = get(),
            )
        }
    }