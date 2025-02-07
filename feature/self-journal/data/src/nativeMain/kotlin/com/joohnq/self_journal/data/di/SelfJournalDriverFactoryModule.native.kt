package com.joohnq.self_journal.data.di

import com.joohnq.self_journal.data.driver.SelfJournalDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val selfJournalDriverFactory: Module = module {
    singleOf(::SelfJournalDriverFactory)
}