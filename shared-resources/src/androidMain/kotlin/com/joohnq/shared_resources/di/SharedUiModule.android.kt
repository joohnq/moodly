package com.joohnq.shared_resources.di

import com.joohnq.shared_resources.ScreenDimensions
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val sharedResourceModule = module {
    singleOf(::ScreenDimensions)
}