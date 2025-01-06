package com.joohnq.core.ui.di

import com.joohnq.core.ui.ScreenDimensions
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val coreUiModule: Module = module {
    singleOf(::ScreenDimensions)
}