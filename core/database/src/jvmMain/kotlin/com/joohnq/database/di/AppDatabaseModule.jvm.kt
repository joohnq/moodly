package com.joohnq.database.di

import com.joohnq.database.AppDatabase
import com.joohnq.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val appDatabaseModule: Module =
    module {
        single<AppDatabase> {
            getDatabaseBuilder()
        }
    }
