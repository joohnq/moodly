package com.joohnq.sleep_quality.domain.di

import com.joohnq.sleep_quality.domain.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.domain.use_case.DeleteSleepQualityUseCase
import com.joohnq.sleep_quality.domain.use_case.GetSleepQualitiesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val sleepQualityDomainModule = module {
    factoryOf(::AddSleepQualityUseCase)
    factoryOf(::GetSleepQualitiesUseCase)
    factoryOf(::DeleteSleepQualityUseCase)
}