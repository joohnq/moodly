package com.joohnq.sleep_quality.ui.di

import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityViewModel
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val sleepQualityUiModule: Module = module {
    viewModelOf(::SleepQualityViewModel)
    viewModelOf(::AddSleepQualityViewModel)
}