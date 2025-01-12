package com.joohnq.stress_level.data.di

import com.joohnq.stress_level.data.driver.StressLevelDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val stressLevelDriverFactoryModule: Module = module {
    singleOf(::StressLevelDriverFactory)
}