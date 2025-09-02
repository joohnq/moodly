package com.joohnq.splash.impl.di

import com.joohnq.splash.impl.SqlMigration
import com.joohnq.splash.impl.ui.presentation.splash.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val splashImplModule: Module =
    module {
        singleOf(::SqlMigration)
        viewModelOf(::SplashViewModel)
    }
