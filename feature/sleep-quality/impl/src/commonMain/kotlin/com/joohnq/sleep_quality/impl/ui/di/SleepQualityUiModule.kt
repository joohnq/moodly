package com.joohnq.sleep_quality.impl.ui.di

import com.joohnq.sleep_quality.impl.ui.presentation.add_sleep_quality.AddSleepQualityViewModel
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sleepQualityUiModule: Module = module {
    singleOf(::SleepQualityViewModel)
    singleOf(::AddSleepQualityViewModel)
}