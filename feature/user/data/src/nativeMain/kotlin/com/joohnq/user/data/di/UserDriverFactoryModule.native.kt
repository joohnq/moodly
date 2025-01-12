package com.joohnq.user.data.di

import com.joohnq.user.data.driver.UserDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val userDriverFactoryModule: Module = module {
    singleOf(::UserDriverFactory)
}