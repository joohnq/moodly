package com.joohnq.mood.add.di

import com.joohnq.mood.add.data.database.MoodDatabase
import com.joohnq.mood.add.data.driver.MoodDriverFactory
import com.joohnq.mood.add.data.repository.MoodRepositoryImpl
import com.joohnq.mood.api.repository.MoodRepository
import com.joohnq.mood.database.MoodDatabaseSql
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val moodImplModule =
    module {
        single<MoodDatabaseSql> {
            val driver = get<MoodDriverFactory>()
            MoodDatabase(driver.createDriver()).invoke()
        }
        singleOf(::MoodRepositoryImpl) bind MoodRepository::class
        includes(moodDriverFactoryModule)
    }
