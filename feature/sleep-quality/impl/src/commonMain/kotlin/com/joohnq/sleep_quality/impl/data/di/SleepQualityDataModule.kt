package com.joohnq.sleep_quality.impl.data.di

import com.joohnq.sleep_quality.api.repository.SleepQualityRepository
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import com.joohnq.sleep_quality.impl.data.database.SleepQualityDatabase
import com.joohnq.sleep_quality.impl.data.driver.SleepQualityDriverFactory
import com.joohnq.sleep_quality.impl.data.repository.SleepQualityRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sleepQualityDataModule =
    module {
        single<SleepQualityDatabaseSql> {
            val driver = get<SleepQualityDriverFactory>()
            SleepQualityDatabase(driver.createDriver()).invoke()
        }
        singleOf(::SleepQualityRepositoryImpl) bind SleepQualityRepository::class
    }
