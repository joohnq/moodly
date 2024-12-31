package com.joohnq.health_journal.data.di

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.health_journal.data.data_source.HealthJournalDataSourceImpl
import com.joohnq.health_journal.data.repository.HealthJournalRepositoryImpl
import com.joohnq.health_journal.database.HealthJournalDatabaseSql
import com.joohnq.health_journal.domain.data_source.HealthJournalDataSource
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module(includes = [HealthJournalDriverFactoryModule::class])
@ComponentScan
class HealthJournalDataModule {
    @Single
    fun provideHealthJournalDatabaseSql(
        sqlDriver: SqlDriver,
    ): HealthJournalDatabaseSql = HealthJournalDatabaseSql(sqlDriver)

    @Single
    fun provideHealthJournalDataSource(
        database: HealthJournalDatabaseSql,
    ): HealthJournalDataSource =
        HealthJournalDataSourceImpl(
            database = database
        )

    @Single(binds = [HealthJournalRepository::class])
    fun provideHealthJournalRepository(
        healthJournalDataSource: HealthJournalDataSource,
    ): HealthJournalRepository =
        HealthJournalRepositoryImpl(
            healthJournalDataSource = healthJournalDataSource
        )
}