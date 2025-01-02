package com.joohnq.mood.data.di

import com.joohnq.mood.data.driver.StatsDriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val moodDriverFactoryModule: org.koin.core.module.Module = module {
    singleOf(::StatsDriverFactory)
}