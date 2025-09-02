package com.joohnq.database.di

import com.joohnq.database.AppDatabase
import com.joohnq.database.AppDatabaseSql
import com.joohnq.database.AppSqlDelightDatabase
import com.joohnq.database.SqliteExceptionMapper
import com.joohnq.database.driver.AppDatabaseDriverFactory
import com.joohnq.database.sqliteExceptionMapper
import com.joohnq.gratefulness.api.entity.dao.GratefulnessDao
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule: Module =
    module {
        includes(appDatabaseDriverFactoryModule)
        includes(appDatabaseModule)
        single<AppDatabaseSql> {
            val driver = get<AppDatabaseDriverFactory>()
            AppSqlDelightDatabase(driver.createDriver()).invoke()
        }
        singleOf<SqliteExceptionMapper>(::sqliteExceptionMapper)
        single<GratefulnessDao> {
            get<AppDatabase>().gratefulnessDao()
        }
    }
