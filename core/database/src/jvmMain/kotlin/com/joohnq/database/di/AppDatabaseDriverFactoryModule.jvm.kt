package com.joohnq.database.di

import com.joohnq.database.driver.AppDatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val appDatabaseDriverFactoryModule: Module =
    module {
        singleOf(::AppDatabaseDriverFactory)
    }
