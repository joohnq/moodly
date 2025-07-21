package com.joohnq.storage.impl.di

import com.joohnq.storage.impl.FileStorageImpl
import com.joohnq.storage.api.FileStorage
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val coreStorageModule: Module = module {
    singleOf(::FileStorageImpl) bind FileStorage::class
}