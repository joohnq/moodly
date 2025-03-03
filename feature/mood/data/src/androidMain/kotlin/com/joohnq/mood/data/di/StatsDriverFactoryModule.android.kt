package com.joohnq.mood.data.di

import com.joohnq.mood.data.driver.MoodDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val moodDriverFactoryModule: Module = module {
    singleOf(::MoodDriverFactory)
}