package com.joohnq.database.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.joohnq.database.AppDatabase
import com.joohnq.database.getDatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

actual val appDatabaseModule: Module =
    module {
        single<AppDatabase> {
            getDatabaseBuilder(get())
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
    }
