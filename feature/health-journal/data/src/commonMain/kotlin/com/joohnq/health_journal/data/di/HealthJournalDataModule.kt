package com.joohnq.health_journal.data.di

import com.joohnq.health_journal.data.database.HealthJournalDatabase
import com.joohnq.health_journal.data.driver.HealthJournalDriverFactory
import com.joohnq.health_journal.data.repository.HealthJournalRepositoryImpl
import com.joohnq.health_journal.database.HealthJournalDatabaseSql
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val healthJournalDataModule = module {
    single<HealthJournalDatabaseSql> {
        val driver = get<HealthJournalDriverFactory>()
        HealthJournalDatabase(driver.createDriver()).invoke()
    }
    singleOf(::HealthJournalRepositoryImpl) bind HealthJournalRepository::class
}