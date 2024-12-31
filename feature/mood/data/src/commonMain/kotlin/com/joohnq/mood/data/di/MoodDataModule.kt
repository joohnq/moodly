package com.joohnq.mood.data.di

import com.joohnq.mood.data.data_source.StatsDataSourceImpl
import com.joohnq.mood.data.database.StatsDatabase
import com.joohnq.mood.data.driver.StatsDriverFactory
import com.joohnq.mood.data.repository.StatsRepositoryImpl
import com.joohnq.mood.database.StatsDatabaseSql
import com.joohnq.mood.domain.repository.StatsDataSource
import com.joohnq.mood.domain.repository.StatsRepository
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module(includes = [StatsDriverFactoryModule::class])
@ComponentScan
class MoodDataModule {
    @Single
    fun provideStatsDatabaseSql(
        statsDriverFactory: StatsDriverFactory,
    ): StatsDatabaseSql = StatsDatabase(
        statsDriverFactory = statsDriverFactory
    ).invoke()

    @Single(binds = [StatsDataSource::class])
    fun provideStatsDataSource(
        database: StatsDatabaseSql,
    ): StatsDataSource = StatsDataSourceImpl(
        database = database
    )

    @Single(binds = [StatsRepository::class])
    fun provideStatsRepository(
        statsDataSource: StatsDataSource,
    ): StatsRepository = StatsRepositoryImpl(
        statsDataSource = statsDataSource
    )
}