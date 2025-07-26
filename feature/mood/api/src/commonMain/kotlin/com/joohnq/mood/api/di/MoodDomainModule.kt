package com.joohnq.mood.api.di

import com.joohnq.mood.api.use_case.AddMoodUseCase
import com.joohnq.mood.api.use_case.DeleteMoodUseCase
import com.joohnq.mood.api.use_case.GetMoodsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val moodDomainModule =
    module {
        factoryOf(::AddMoodUseCase)
        factoryOf(::DeleteMoodUseCase)
        factoryOf(::GetMoodsUseCase)
    }
