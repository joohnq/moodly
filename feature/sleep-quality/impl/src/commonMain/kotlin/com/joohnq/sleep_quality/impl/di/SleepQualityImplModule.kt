package com.joohnq.sleep_quality.impl.di

import com.joohnq.sleep_quality.api.repository.SleepQualityRepository
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import com.joohnq.sleep_quality.impl.data.database.SleepQualityDatabase
import com.joohnq.sleep_quality.impl.data.driver.SleepQualityDriverFactory
import com.joohnq.sleep_quality.impl.data.repository.SleepQualityRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sleepQualityImplModule =
    module {
        single<SleepQualityDatabase> {
            val driver = get<SleepQualityDriverFactory>().createDriver()
            SleepQualityDatabase(driver)
        }
        single<SleepQualityDatabaseSql> {
            val db = get<SleepQualityDatabase>()
            db.invoke()
        }
        singleOf(::SleepQualityRepositoryImpl) bind SleepQualityRepository::class
        includes(sleepQualityDriverFactoryModule)
    }
