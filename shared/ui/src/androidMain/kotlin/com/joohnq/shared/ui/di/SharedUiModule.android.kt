package com.joohnq.shared.ui.di

import com.joohnq.shared.ui.ScreenDimensions
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val sharedUiModule = module {
    singleOf(::ScreenDimensions)
}