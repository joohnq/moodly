package com.joohnq.sleep_quality.impl.data.di

import com.joohnq.sleep_quality.impl.data.driver.SleepQualityDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val sleepQualityDriverFactoryModule: Module =
    module {
        singleOf(::SleepQualityDriverFactory)
    }
