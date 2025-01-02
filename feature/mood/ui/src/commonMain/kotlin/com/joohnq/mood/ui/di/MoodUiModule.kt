package com.joohnq.mood.ui.di

import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatViewModel
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val moodUiModule = module {
    viewModelOf(::StatsViewModel)
    viewModelOf(::AddStatViewModel)
}