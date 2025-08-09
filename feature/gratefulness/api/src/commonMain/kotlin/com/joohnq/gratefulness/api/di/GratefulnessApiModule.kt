package com.joohnq.gratefulness.api.di

import com.joohnq.gratefulness.api.use_case.AddGratefulnessUseCase
import com.joohnq.gratefulness.api.use_case.DeleteGratefulnessUseCase
import com.joohnq.gratefulness.api.use_case.GetGratefulnessUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val gratefulnessApiModule: Module =
    module {
        singleOf(::AddGratefulnessUseCase)
        singleOf(::DeleteGratefulnessUseCase)
        singleOf(::GetGratefulnessUseCase)
    }
