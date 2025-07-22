package com.joohnq.sleep_quality.impl.ui.di

import com.joohnq.sleep_quality.impl.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityViewModel
import com.joohnq.sleep_quality.impl.ui.viewmodel.SleepQualityViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sleepQualityUiModule: Module = module {
    singleOf(::SleepQualityViewModel)
    singleOf(::AddSleepQualityViewModel)
}