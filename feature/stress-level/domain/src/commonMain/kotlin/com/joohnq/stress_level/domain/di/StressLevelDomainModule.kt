package com.joohnq.stress_level.domain.di

import com.joohnq.stress_level.domain.repository.StressLevelRepository
import com.joohnq.stress_level.domain.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.domain.use_case.GetStressLevelsUseCase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class StressLevelDomainModule {
    @Factory
    fun provideAddStressLevelUseCase(
        stressLevelRepository: StressLevelRepository,
    ): AddStressLevelUseCase = AddStressLevelUseCase(
        stressLevelRepository = stressLevelRepository
    )

    @Factory
    fun provideGetStressLevelsUseCase(
        stressLevelRepository: StressLevelRepository,
    ): GetStressLevelsUseCase = GetStressLevelsUseCase(
        stressLevelRepository = stressLevelRepository
    )
}