package com.joohnq.sleep_quality.data.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import com.joohnq.sleep_quality.data.driver.SleepQualityDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val sleepQualityDriverFactoryModule: Module = module {
    single<SqlDriver> {
        val context = get<Context>()
        SleepQualityDriverFactory(context).createDriver()
    }
}