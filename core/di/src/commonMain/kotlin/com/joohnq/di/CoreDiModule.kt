package com.joohnq.di

import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.core.ui.IDatetimeProvider
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDiModule = module {
    single {
        DatetimeProvider
    } bind IDatetimeProvider::class
}