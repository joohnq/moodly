package com.joohnq.stress_level.ui.di

import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelViewModel
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val stressLevelUiModule = module {
    viewModelOf(::StressLevelViewModel)
    viewModelOf(::AddStressLevelViewModel)
}