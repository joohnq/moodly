package com.joohnq.database.di

import com.joohnq.database.AppDatabase
import com.joohnq.database.SqliteExceptionMapper
import com.joohnq.database.sqliteExceptionMapper
import com.joohnq.gratefulness.api.entity.dao.GratefulnessDao
import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule: Module =
    module {
        includes(appDatabaseModule)
        single<SqliteExceptionMapper> {
            sqliteExceptionMapper
        }
        single<GratefulnessDao> {
            get<AppDatabase>().gratefulnessDao()
        }
    }
