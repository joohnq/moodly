package com.joohnq.sleep_quality.add.di

import com.joohnq.sleep_quality.add.presentation.AddSleepQualityViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sleepQualityAddModule: Module =
    module {
        viewModel {
            AddSleepQualityViewModel(
                addSleepQualityUseCase = get()
            )
        }
    }