package com.joohnq.mood.impl.data.di

import com.joohnq.mood.impl.data.driver.MoodDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val moodDriverFactoryModule: Module = module {
    singleOf(::MoodDriverFactory)
}