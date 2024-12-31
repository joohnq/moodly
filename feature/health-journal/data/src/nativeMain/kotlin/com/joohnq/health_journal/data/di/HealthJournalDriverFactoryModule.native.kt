package com.joohnq.health_journal.data.di

import com.joohnq.health_journal.data.driver.HealthJournalDriverFactory
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
actual class HealthJournalDriverFactoryModule {
    @Single
    fun provideHealthJournalDriverFactory(): HealthJournalDriverFactory =
        HealthJournalDriverFactory()
}