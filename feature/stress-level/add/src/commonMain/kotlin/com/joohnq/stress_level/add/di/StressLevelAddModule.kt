package com.joohnq.stress_level.add.di

import com.joohnq.stress_level.add.presentation.AddStressLevelViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val stressLevelAddModule: Module =
    module {
        single<AddStressLevelViewModel> {
            AddStressLevelViewModel(
                addStressLevelUseCase = get()
            )
        }
    }
