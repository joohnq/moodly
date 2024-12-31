package com.joohnq.sleep_quality.data.di

import com.joohnq.sleep_quality.data.driver.SleepQualityDriverFactory
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
actual class SleepQualityDriverFactoryModule {
    @Single
    fun provideSleepQualityDriverFactory(): SleepQualityDriverFactory =
        SleepQualityDriverFactory()
}