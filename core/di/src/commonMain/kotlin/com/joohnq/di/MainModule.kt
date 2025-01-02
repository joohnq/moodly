package com.joohnq.di

import com.joohnq.shared.domain.DatetimeProvider
import com.joohnq.shared.domain.IDatetimeProvider
import org.koin.dsl.bind
import org.koin.dsl.module

val mainModule = module {
    single {
        DatetimeProvider
    } bind IDatetimeProvider::class
}