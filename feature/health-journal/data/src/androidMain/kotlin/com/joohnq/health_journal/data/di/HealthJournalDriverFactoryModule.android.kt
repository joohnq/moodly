package com.joohnq.health_journal.data.di

import com.joohnq.health_journal.data.driver.HealthJournalDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val healthJournalDriverFactory: Module = module {
    singleOf(::HealthJournalDriverFactory)
}