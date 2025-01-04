package com.joohnq.sleep_quality.ui.di

import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityViewModel
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sleepQualityUiModule = module {
    singleOf(::SleepQualityViewModel)
    singleOf(::AddSleepQualityViewModel)
}