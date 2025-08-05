package com.joohnq.sleep_quality.add.di

import com.joohnq.sleep_quality.add.presentation.AddSleepQualityViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val sleepQualityAddModule: Module =
    module {
        single {
            AddSleepQualityViewModel(
                addSleepQualityUseCase = get()
            )
        }
    }
