package com.joohnq.stress_level.api.di

import com.joohnq.stress_level.api.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.api.use_case.DeleteStressLevelUseCase
import com.joohnq.stress_level.api.use_case.GetStressLevelsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val stressLevelDomainModule =
    module {
        factoryOf(::AddStressLevelUseCase)
        factoryOf(::GetStressLevelsUseCase)
        factoryOf(::DeleteStressLevelUseCase)
    }
