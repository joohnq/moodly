package com.joohnq.mood.data.di

import com.joohnq.sleep_quality.data.driver.SleepQualityDriverFactory
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
actual class StatsDriverFactoryModule {
    @Single
    fun provideStatsDriverFactory(): SleepQualityDriverFactory =
        SleepQualityDriverFactory()
}