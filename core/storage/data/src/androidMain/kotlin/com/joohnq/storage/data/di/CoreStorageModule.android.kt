package com.joohnq.storage.data.di

import com.joohnq.storage.data.FileStorageImpl
import com.joohnq.storage.domain.FileStorage
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val coreStorageModule: Module = module {
    singleOf(::FileStorageImpl) bind FileStorage::class
}