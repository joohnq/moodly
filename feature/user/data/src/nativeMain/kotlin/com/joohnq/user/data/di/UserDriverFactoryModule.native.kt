package com.joohnq.user.data.di

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.user.data.driver.UserDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val userDriverFactoryModule: Module = module {
    single<SqlDriver> {
        UserDriverFactory().createDriver()
    }
}