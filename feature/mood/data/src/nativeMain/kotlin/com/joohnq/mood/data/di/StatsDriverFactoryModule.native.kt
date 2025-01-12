package com.joohnq.mood.data.di

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.mood.data.driver.StatsDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val moodDriverFactoryModule: Module = module {
    single<SqlDriver> {
        StatsDriverFactory().createDriver()
    }
}