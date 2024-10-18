package com.joohnq.moodapp.di

import com.joohnq.moodapp.ScreenDimensions
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::ScreenDimensions)
}