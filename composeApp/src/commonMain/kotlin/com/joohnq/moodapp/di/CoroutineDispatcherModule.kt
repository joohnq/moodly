package com.joohnq.moodapp.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coroutineDispatcherModule: Module = module {
    single<CoroutineDispatcher>(qualifier = named("IO")) { Dispatchers.IO }
}