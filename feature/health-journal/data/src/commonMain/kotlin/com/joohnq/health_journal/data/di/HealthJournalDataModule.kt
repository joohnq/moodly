package com.joohnq.health_journal.data.di

import com.joohnq.health_journal.data.data_source.HealthJournalDataSourceImpl
import com.joohnq.health_journal.data.database.HealthJournalDatabase
import com.joohnq.health_journal.data.repository.HealthJournalRepositoryImpl
import com.joohnq.health_journal.domain.data_source.HealthJournalDataSource
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val healthJournalDataModule = module {
    singleOf(::HealthJournalDatabase)
    singleOf(HealthJournalDatabase::invoke)
    singleOf(::HealthJournalDataSourceImpl) bind HealthJournalDataSource::class
    singleOf(::HealthJournalRepositoryImpl) bind HealthJournalRepository::class
}