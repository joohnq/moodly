package com.joohnq.sleep_quality.api.di

import com.joohnq.sleep_quality.api.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.api.use_case.DeleteSleepQualityUseCase
import com.joohnq.sleep_quality.api.use_case.GetSleepQualitiesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val sleepQualityApiModule =
    module {
        factoryOf(::AddSleepQualityUseCase)
        factoryOf(::GetSleepQualitiesUseCase)
        factoryOf(::DeleteSleepQualityUseCase)
    }
