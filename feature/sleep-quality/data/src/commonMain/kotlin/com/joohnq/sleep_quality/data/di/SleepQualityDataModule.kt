package com.joohnq.sleep_quality.data.di

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.sleep_quality.data.database.SleepQualityDatabase
import com.joohnq.sleep_quality.data.repository.SleepQualityRepositoryImpl
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sleepQualityDataModule = module {
    single<SleepQualityDatabaseSql> {
        val driver = get<SqlDriver>()
        SleepQualityDatabase(driver).invoke()
    }
    singleOf(::SleepQualityRepositoryImpl) bind SleepQualityRepository::class
}