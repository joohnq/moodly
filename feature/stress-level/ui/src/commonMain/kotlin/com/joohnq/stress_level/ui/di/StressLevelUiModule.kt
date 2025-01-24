package com.joohnq.stress_level.ui.di

import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelViewModel
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val stressLevelUiModule: Module = module {
    singleOf(::StressLevelViewModel)
    singleOf(::AddStressLevelViewModel)
}