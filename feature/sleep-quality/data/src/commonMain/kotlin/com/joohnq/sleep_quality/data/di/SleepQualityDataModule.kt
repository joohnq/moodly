package com.joohnq.sleep_quality.data.di

import com.joohnq.sleep_quality.data.data_source.SleepQualityDataSourceImpl
import com.joohnq.sleep_quality.data.database.SleepQualityDatabase
import com.joohnq.sleep_quality.data.driver.SleepQualityDriverFactory
import com.joohnq.sleep_quality.data.repository.SleepQualityRepositoryImpl
import com.joohnq.sleep_quality.domain.repository.SleepQualityDataSource
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class SleepQualityDataModule {
    @Single
    fun provideSleepQualityDataSource(
        sleepQualityDriverFactory: SleepQualityDriverFactory,
    ): SleepQualityDatabaseSq = SleepQualityDatabase(
        sleepQualityDriverFactory = sleepQualityDriverFactory
    ).invoke()

    @Single(binds = [SleepQualityDataSource::class])
    fun provideSleepQualityDataSource(
        database: SleepQualityDatabaseSql,
    ): SleepQualityDataSource = SleepQualityDataSourceImpl(
        database = database
    )

    @Single(binds = [SleepQualityRepository::class])
    fun provideSleepQualityRepository(
        sleepQualityDataSource: SleepQualityDataSource,
    ): SleepQualityRepository = SleepQualityRepositoryImpl(
        sleepQualityDataSource = sleepQualityDataSource
    )
}