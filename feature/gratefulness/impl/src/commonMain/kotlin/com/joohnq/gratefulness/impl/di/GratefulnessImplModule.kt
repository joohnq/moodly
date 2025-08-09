package com.joohnq.gratefulness.impl.di

import com.joohnq.gratefulness.api.repository.GratefulnessRepository
import com.joohnq.gratefulness.impl.data.repository.GratefulnessRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val gratefulnessImplModule: Module =
    module {
        singleOf(::GratefulnessRepositoryImpl) bind GratefulnessRepository::class
    }
