package com.joohnq.health_journal.data.di

import com.joohnq.health_journal.data.data_source.HealthJournalDataSourceImpl
import com.joohnq.health_journal.data.repository.HealthJournalRepositoryImpl
import com.joohnq.health_journal.database.HealthJournalDatabaseSql
import com.joohnq.health_journal.domain.data_source.HealthJournalDataSource
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val healthJournalDataModule = module {
    single { HealthJournalDatabaseSql(get()) }
    single<HealthJournalDataSource> { HealthJournalDataSourceImpl(get()) } bind (HealthJournalDataSource::class)
    single<HealthJournalRepository> { HealthJournalRepositoryImpl(get()) } bind (HealthJournalRepository::class)
}