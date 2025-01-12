package com.joohnq.sleep_quality.data.di

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.sleep_quality.data.driver.SleepQualityDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sleepQualityDriverFactoryModule: Module = module {
    single<SqlDriver> {
        SleepQualityDriverFactory().createDriver()
    }
}