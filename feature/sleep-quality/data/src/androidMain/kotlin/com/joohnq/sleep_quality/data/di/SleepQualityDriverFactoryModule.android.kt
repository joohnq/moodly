package com.joohnq.sleep_quality.data.di

import android.content.Context
import com.joohnq.sleep_quality.data.driver.SleepQualityDriverFactory
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
actual class SleepQualityDriverFactoryModule {
    @Single
    fun provideSleepQualityDriverFactory(context: Context): SleepQualityDriverFactory =
        SleepQualityDriverFactory(context)
}