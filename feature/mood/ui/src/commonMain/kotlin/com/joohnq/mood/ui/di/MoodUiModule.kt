package com.joohnq.mood.ui.di

import com.joohnq.mood.domain.use_case.AddStatsUseCase
import com.joohnq.mood.domain.use_case.DeleteStatsUseCase
import com.joohnq.mood.domain.use_case.GetStatsUseCase
import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatViewModel
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan
class MoodUiModule {
    @KoinViewModel
    fun provideStatsViewModel(
        getStatsUseCase: GetStatsUseCase,
        deleteStatsUseCase: DeleteStatsUseCase,
        addStatsUseCase: AddStatsUseCase,
        dispatcher: CoroutineDispatcher,
    ): StatsViewModel = StatsViewModel(
        getStatsUseCase = getStatsUseCase,
        deleteStatsUseCase = deleteStatsUseCase,
        addStatsUseCase = addStatsUseCase,
        dispatcher = dispatcher
    )

    @KoinViewModel
    fun provideAddStatViewModel(): AddStatViewModel = AddStatViewModel()
}