package com.joohnq.di

import com.joohnq.domain.DatetimeProvider
import com.joohnq.domain.IDatetimeProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.annotation.Module

@Module
class MainModule {
    fun provideIDatetimeProvider(): IDatetimeProvider = DatetimeProvider
    fun provideIoCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}