package com.joohnq.user.impl.data.di

import com.joohnq.user.impl.data.driver.UserDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val userDriverFactoryModule: Module =
    module {
        singleOf(::UserDriverFactory)
    }
