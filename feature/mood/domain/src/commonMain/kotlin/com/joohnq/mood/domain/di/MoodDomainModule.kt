package com.joohnq.mood.domain.di

import com.joohnq.mood.domain.use_case.AddMoodUseCase
import com.joohnq.mood.domain.use_case.DeleteMoodUseCase
import com.joohnq.mood.domain.use_case.GetMoodsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val moodDomainModule = module {
    factoryOf(::AddMoodUseCase)
    factoryOf(::DeleteMoodUseCase)
    factoryOf(::GetMoodsUseCase)
}