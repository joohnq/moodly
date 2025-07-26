package com.joohnq.self_journal.impl.data.di

import com.joohnq.self_journal.impl.data.driver.SelfJournalDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val selfJournalDriverFactory: Module =
    module {
        singleOf(::SelfJournalDriverFactory)
    }
