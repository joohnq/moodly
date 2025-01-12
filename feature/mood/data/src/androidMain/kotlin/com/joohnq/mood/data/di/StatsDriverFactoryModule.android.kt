package com.joohnq.mood.data.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import com.joohnq.mood.data.driver.StatsDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val moodDriverFactoryModule: Module = module {
    single<SqlDriver> {
        val context = get<Context>()
        StatsDriverFactory(context).createDriver()
    }
}