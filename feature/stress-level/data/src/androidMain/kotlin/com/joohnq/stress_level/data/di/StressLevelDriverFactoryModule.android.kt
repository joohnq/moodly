package com.joohnq.stress_level.data.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import com.joohnq.stress_level.data.driver.StressLevelDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val stressLevelDriverFactoryModule: Module = module {
    single<SqlDriver> {
        val context = get<Context>()
        StressLevelDriverFactory(context).createDriver()
    }
}