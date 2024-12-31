package com.joohnq.mood.data.di

import android.content.Context
import com.joohnq.mood.data.driver.StatsDriverFactory
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
actual class StatsDriverFactoryModule {
    @Single
    fun provideStatsDriverFactory(context: Context): StatsDriverFactory =
        StatsDriverFactory(context)
}