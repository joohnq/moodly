package com.joohnq.stress_level.data.di

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.stress_level.data.driver.StressLevelDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val stressLevelDriverFactoryModule: Module = module {
    single<SqlDriver> {
        StressLevelDriverFactory().createDriver()
    }
}