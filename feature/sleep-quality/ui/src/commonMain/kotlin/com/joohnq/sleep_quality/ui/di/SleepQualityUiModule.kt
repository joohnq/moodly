package com.joohnq.sleep_quality.ui.di

import com.joohnq.sleep_quality.domain.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.domain.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityViewModel
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan
class SleepQualityUiModule {
    @KoinViewModel
    fun provideSleepQualityViewModel(
        addSleepQualityUseCase: AddSleepQualityUseCase,
        getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
        dispatcher: CoroutineDispatcher,
    ): SleepQualityViewModel = SleepQualityViewModel(
        addSleepQualityUseCase = addSleepQualityUseCase,
        getSleepQualitiesUseCase = getSleepQualitiesUseCase,
        dispatcher = dispatcher
    )

    @KoinViewModel
    fun provideAddSleepQualityViewModel(): AddSleepQualityViewModel =
        AddSleepQualityViewModel()
}