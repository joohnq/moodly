package com.joohnq.self_journal.impl.di

import com.joohnq.self_journal.api.repository.SelfJournalRepository
import com.joohnq.self_journal.database.SelfJournalDatabaseSql
import com.joohnq.self_journal.impl.data.database.SelfJournalDatabase
import com.joohnq.self_journal.impl.data.driver.SelfJournalDriverFactory
import com.joohnq.self_journal.impl.data.repository.SelfJournalRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val selfJournalImplModule =
    module {
        single<SelfJournalDatabaseSql> {
            val driver = get<SelfJournalDriverFactory>()
            SelfJournalDatabase(driver.createDriver()).invoke()
        }
        singleOf(::SelfJournalRepositoryImpl) bind SelfJournalRepository::class
        includes(selfJournalDriverFactory)
    }
