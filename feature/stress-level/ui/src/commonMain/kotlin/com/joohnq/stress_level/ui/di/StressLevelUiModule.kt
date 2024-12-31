package com.joohnq.stress_level.ui.di

import com.joohnq.stress_level.domain.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.domain.use_case.GetStressLevelsUseCase
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelViewModel
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan
class StressLevelUiModule {
    @KoinViewModel
    fun provideStressLevelViewModel(
        addStressLevelUseCase: AddStressLevelUseCase,
        getStressLevelsUseCase: GetStressLevelsUseCase,
        dispatcher: CoroutineDispatcher,
    ): StressLevelViewModel = StressLevelViewModel(
        addStressLevelUseCase = addStressLevelUseCase,
        getStressLevelsUseCase = getStressLevelsUseCase,
        dispatcher = dispatcher
    )

    @KoinViewModel
    fun provideAddStressLevelViewModel(): AddStressLevelViewModel = AddStressLevelViewModel()
}