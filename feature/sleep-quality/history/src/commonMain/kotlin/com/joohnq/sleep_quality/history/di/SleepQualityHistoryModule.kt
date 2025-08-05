package com.joohnq.sleep_quality.history.di

import com.joohnq.sleep_quality.history.presentation.SleepQualityHistoryViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val sleepQualityHistoryModule: Module =
    module {
        single {
            SleepQualityHistoryViewModel(
                deleteSleepQualityUseCase = get()
            )
        }
    }
