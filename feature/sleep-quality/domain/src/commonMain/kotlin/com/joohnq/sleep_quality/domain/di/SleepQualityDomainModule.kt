package com.joohnq.sleep_quality.domain.di

import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import com.joohnq.sleep_quality.domain.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.domain.use_case.GetSleepQualitiesUseCase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class SleepQualityDomainModule {
    @Factory
    fun provideAddSleepQualityUseCase(
        sleepQualityRepository: SleepQualityRepository,
    ): AddSleepQualityUseCase = AddSleepQualityUseCase(
        sleepQualityRepository = sleepQualityRepository
    )

    @Factory
    fun provideGetSleepQualitiesUseCase(
        sleepQualityRepository: SleepQualityRepository,
    ): GetSleepQualitiesUseCase = GetSleepQualitiesUseCase(
        sleepQualityRepository = sleepQualityRepository
    )
}