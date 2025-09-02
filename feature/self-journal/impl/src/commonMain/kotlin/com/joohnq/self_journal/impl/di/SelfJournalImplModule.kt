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
        single<SelfJournalDatabase> {
            val driver = get<SelfJournalDriverFactory>().createDriver()
            SelfJournalDatabase(driver)
        }
        single<SelfJournalDatabaseSql> {
            val db = get<SelfJournalDatabase>()
            db.invoke()
        }
        singleOf(::SelfJournalRepositoryImpl) bind SelfJournalRepository::class
        includes(selfJournalDriverFactory)
    }
